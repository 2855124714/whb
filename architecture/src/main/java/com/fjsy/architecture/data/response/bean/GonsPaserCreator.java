package com.fjsy.architecture.data.response.bean;

import com.fjsy.architecture.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import androidx.annotation.NonNull;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class GonsPaserCreator extends Converter.Factory {

    private final Gson gson;

    private GonsPaserCreator(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    public static GonsPaserCreator create() {
        return create(new Gson());
    }

    public static GonsPaserCreator create(Gson gson) {
        return new GonsPaserCreator(gson);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type, Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type,
                parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeToken typeToken = TypeToken.get(type);
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        if (typeToken.getRawType() == BaseBean.class) {
            return new BaseResponseBodyConverter<>();
        }
        if (BaseBean.class.isAssignableFrom(typeToken.getRawType())) {
            if (ArrayBean.class.isAssignableFrom(typeToken.getRawType())) {
                return new ArrayResponseBodyConverter<>(type);
            } else {
                return new ObjectResponseBodyConverter<>(type);
            }
        }
        return new OtherResponseBodyConverter<>(gson, adapter);
    }

    private class BaseResponseBodyConverter<T> implements
            Converter<ResponseBody, BaseBean> {
        @Override
        public BaseBean convert(@NonNull ResponseBody value) {
            BaseValueBean BaseBean = gson.fromJson(value.charStream(), BaseValueBean.class);
            BaseBean resultBean = new BaseBean();
            resultBean.statusInfo.statusCode = BaseBean.statusCode;
            resultBean.statusInfo.statusMessage = BaseBean.statusMessage;
            resultBean.resultData = BaseBean.resultData;
            resultBean.count = BaseBean.count;
            return resultBean;
        }
    }

    private class ObjectResponseBodyConverter<T extends BaseBean> implements
            Converter<ResponseBody, T> {
        private Type type;

        ObjectResponseBodyConverter(Type type) {
            this.type = type;
        }

        @Override
        public T convert(@NonNull ResponseBody value) throws IOException {
            T resultBean = null;
            try {
                JsonObject json = (JsonObject) JsonParser.parseString(value.string());
                JsonElement jsonElement = json.get("data");
                String jsonStr;
                if (jsonElement != null && jsonElement.isJsonObject()) {
                    jsonStr = jsonElement.getAsJsonObject().toString();
                } else {
                    jsonStr = "{}";
                }
                resultBean = gson.fromJson(jsonStr, this.type);
                resultBean.statusInfo.statusCode = json.get("code").getAsInt();
                resultBean.statusInfo.statusMessage = json.get("msg").getAsString();
                resultBean.count = json.get("count").getAsInt();
                resultBean.resultData = jsonElement != null ? jsonElement.toString() : "";
            } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                Logger.e(e);
            }

            return resultBean;
        }
    }

    private class ArrayResponseBodyConverter<T extends ArrayBean> implements
            Converter<ResponseBody, T> {
        private Type type;

        ArrayResponseBodyConverter(Type type) {
            this.type = type;
        }

        @Override
        public T convert(@NonNull ResponseBody value) throws IOException {
            T resultBean = null;
            try {
                JsonObject json = (JsonObject) JsonParser.parseString(value.string());
                JsonElement jsonElement = json.get("data");
                String jsonStr = "[]";
                int count = 0;
                if (jsonElement != null) {
                    if (jsonElement.isJsonObject()){
                        JsonObject asJsonObject = jsonElement.getAsJsonObject();
                        if (asJsonObject != null) {
                            JsonElement total = asJsonObject.get("count");
                            if (total != null) {
                                count = total.getAsInt();
                            }
                            JsonElement data = asJsonObject.get("data");
                            if (data != null && data.isJsonArray()) {
                                jsonStr = data.getAsJsonArray().toString();
                            }
                        }
                    }else if (jsonElement.isJsonArray()){
                        JsonArray asJsonArray = jsonElement.getAsJsonArray();
                        if (asJsonArray != null){
                            jsonStr = asJsonArray.toString();
                        }
                    }

                }
                resultBean = gson.fromJson(String.format("{\"data\":%s}", jsonStr), this.type);
                resultBean.statusInfo.statusCode = json.get("code").getAsInt();
                resultBean.statusInfo.statusMessage = json.get("msg").getAsString();
                resultBean.count = count==0?json.get("count").getAsInt():count;
                resultBean.resultData = jsonElement != null ? jsonElement.toString() : "";
            } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                Logger.e(e);
            }

            return resultBean;
        }
    }

    private class OtherResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Charset UTF_8 = Charset.forName("UTF-8");
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        OtherResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public T convert(@NonNull ResponseBody value) throws IOException {
            String response = value.string();
            MediaType contentType = value.contentType();
            Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
            InputStream inputStream = new ByteArrayInputStream(response.getBytes());
            Reader reader = null;
            JsonReader jsonReader = null;
            if (charset != null) {
                reader = new InputStreamReader(inputStream, charset);
                jsonReader = gson.newJsonReader(reader);
            }

            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }
    }
    
    private static class BaseValueBean {
        @SerializedName("code")
        int statusCode;
        @SerializedName("msg")
        String statusMessage;
        @SerializedName("data")
        String resultData;
        int count;
    }
}
