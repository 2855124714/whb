package com.fjsy.architecture.net.upload;

import com.fjsy.architecture.data.response.bean.ModelLiveData;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by leo
 * on 2019/8/20.
 */
public class UploadFileRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    ModelLiveData liveData;

    public UploadFileRequestBody(File file, ModelLiveData liveData) {
//        this.mRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        this.mRequestBody = RequestBody.create(MediaType.parse("image/*"),file);
        this.liveData = liveData;
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        //写入
        mRequestBody.writeTo(bufferedSink);

        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            if (liveData != null) {
                ModelLiveData.LiveDataWrapper value = liveData.getValue();
//                value.data
//                value.precent = (int) (bytesWritten * 100 / contentLength());
//                value.total = contentLength();
                liveData.postValue(value);
            }
        }
    }
}

