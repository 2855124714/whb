package com.fjsy.architecture.net;

import com.fjsy.architecture.app.BaseApp;
import com.fjsy.architecture.global.data.constants.Constants;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

public class ResourceNetwork extends BaseNetwork {

    public ResourceNetwork() {
        this(Constants.baseUrl);
    }

    public ResourceNetwork(String baseUrl) {
        super(baseUrl);
    }

    @Override
    protected Headers buildHeaders(RequestBody requestBody) {
        Headers.Builder builder = new Headers.Builder();
        builder.add("requestSource", "Android");
        return builder.build();
    }

    @Override
    protected Request convertGetRequest(Request request, HttpUrl httpUrl) {
        HttpUrl.Builder builder = httpUrl.newBuilder();
        return request.newBuilder()
                .url(builder.build())
                .headers(buildHeaders(request.body()))
                .build();
    }

    @Override
    protected RequestBody convertBody(FormBody formBody) {
        FormBody.Builder builder = new FormBody.Builder();
        int size = formBody.size();
        for (int index = 0; index < size; index++) {
            builder.add(formBody.name(index), formBody.value(index));
        }

        return builder.build();
    }

    @Override
    protected RequestBody convertBody(MultipartBody multipartBody) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (MultipartBody.Part part : multipartBody.parts()) {
            builder.addPart(part);
        }
        return builder.build();
    }

    @Override
    protected RequestBody convertBody(final RequestBody requestBody) {
        final String paramsStr = bodyToString(requestBody);
        System.out.println(paramsStr);

        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                try {
                    Buffer buffer = new Buffer();
                    Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
                    writer.write(paramsStr);
                    writer.close();
                    sink.write(buffer.readByteString());
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        };
    }
}
