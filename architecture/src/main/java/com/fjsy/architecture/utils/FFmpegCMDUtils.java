package com.fjsy.architecture.utils;

import android.annotation.SuppressLint;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import io.microshow.rxffmpeg.RxFFmpegCommandList;

/**
 * Created by Czm on 2019/11/12 09:28
 */
public class FFmpegCMDUtils {

    /**
     * @param path       音/视频路径
     * @param volume     音量
     * @param outputPath 音/视频频输出路径
     * @return ffmpeg  -i input.mp3 -filter：“volume = 5dB” output.mp3
     */
    public static String[] setVolume(String path, int volume, String outputPath) {
        String[] commands = new String[6];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = path;
        commands[3] = "-filter:a";
//        commands[4] = "\"volume = " + (double)volume/100d + "dB\"";
        commands[4] = "volume=" + (double) volume / 100d;
        commands[5] = outputPath;
        return commands;
    }

    /**
     * 修改音频文件的音量
     *
     * @param audioOrMusicUrl
     * @param vol
     * @param outUrl
     * @return
     */
    public static String[] changeAudioOrMusicVol(String audioOrMusicUrl, int vol, String outUrl) {
        Log.w("SLog", "audioOrMusicUrl:" + audioOrMusicUrl + "\nvol:" + vol + "\noutUrl:" + outUrl);
        String[] commands = new String[8];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = audioOrMusicUrl;
        commands[3] = "-vol";
        commands[4] = String.valueOf(vol);
        commands[5] = "-acodec";
        commands[6] = "copy";
        commands[7] = outUrl;
        return commands;
    }

    /**
     * 提取单独的音频
     *
     * @param videoUrl
     * @param outUrl   后缀要ac
     * @return
     */
    public static String[] extractAudio(String videoUrl, String outUrl) {
        String[] commands = new String[8];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = videoUrl;
        commands[3] = "-acodec";
        commands[4] = "copy";
        commands[5] = "-vn";
        commands[6] = "-y";
        commands[7] = outUrl;
        return commands;
    }

    /**
     * 提取单独的视频，没有声音
     *
     * @param videoUrl
     * @param outUrl
     * @return
     */
    public static String[] extractVideo(String videoUrl, String outUrl) {
        //ffmpeg -i input.mp4 -an output.mp4
        String[] commands = new String[8];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = videoUrl;
        commands[3] = "-vcodec";
        commands[4] = "copy";
        commands[5] = "-an";
        commands[6] = "-y";
        commands[7] = outUrl;
        return commands;
    }

    /**
     * 合成音频
     *
     * @param audio1    音频1
     * @param audio2    音频2
     * @param outputUrl 后缀aac
     * @return
     */
    public static String[] composeAudio(String audio1, String audio2, String outputUrl) {
//        Log.w("SLog","audio1:" + audio1 + "\naudio2:" + audio2 + "\noutputUrl:" + outputUrl);
        String[] commands = new String[10];
        commands[0] = "ffmpeg";
        //输入
        commands[1] = "-i";
        commands[2] = audio1;
        //音乐
        commands[3] = "-i";
        commands[4] = audio2;
        //覆盖输出
        commands[5] = "-filter_complex";
        commands[6] = "amix=inputs=2:duration=first:dropout_transition=2";
        commands[7] = "-strict";
        commands[8] = "-2";
        //输出文件
        commands[9] = outputUrl;
        return commands;
    }

    /**
     * 音频，视频合成
     *
     * @param videoUrl     视频路径
     * @param musicOrAudio 音频路径
     * @param outputUrl    合成路径
     * @param second       视频长度
     * @return
     */
    public static String[] composeVideo(String videoUrl, String musicOrAudio, String outputUrl, long second) {
        Log.w("SLog", "videoUrl:" + videoUrl + "\nmusicOrAudio:" + musicOrAudio + "\noutputUrl:" + outputUrl + "\nsecond:" + second);
        //        ffmpeg -i son.wav -i video_origine.avi video_finale.mpg
        String[] commands = new String[14];
        commands[0] = "ffmpeg";
        //输入
        commands[1] = "-i";
        commands[2] = videoUrl;
        //音乐
        commands[3] = "-i";
        commands[4] = musicOrAudio;
        commands[5] = "-ss";
        commands[6] = "00:00:00";
        commands[7] = "-t";
        commands[8] = String.valueOf(second);
        //覆盖输出
        commands[9] = "-vcodec";
        commands[10] = "copy";
        commands[11] = "-acodec";
        commands[12] = "copy";
        //输出文件
        commands[13] = outputUrl;
        return commands;
    }

    /**
     * 音频视频合成
     * ffmpeg.exe -i 1.mp4 -i 1.mp3 -vcodec copy -acodec copy 1.mp4
     */
    public static String[] composeVideoMusic(String videoUrl, String musicOrAudio, String outputUrl) {
        // ffmpeg -i 1.mp4 -i 1.mp3 -vcodec copy -acodec copy 1.mp4
        String[] commands = new String[10];
        commands[0] = "ffmpeg";
        //输入
        commands[1] = "-i";
        commands[2] = videoUrl;
        //音乐
        commands[3] = "-i";
        commands[4] = musicOrAudio;
        commands[5] = "-vcodec";
        commands[6] = "copy";
        //覆盖输出
        commands[7] = "-acodec";
        commands[8] = "copy";
        //输出文件
        commands[9] = outputUrl;
        return commands;
    }

    /**
     * 音频视频合成
     * ffmpeg -y -i videoUrl -i musicOrAudio -filter_complex [0:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=0.2[a0];[1:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1[a1];[a0][a1]amix=inputs=2:duration=first[aout] -map [aout] -ac 2 -c:v copy -map 0:v:0 -preset superfast /storage/emulated/0/1/result.mp4
     */
    public static String[] composeVideoMusic(String videoUrl, String musicOrAudio, long startTime, long second, float videoVolume, float audioVolume, String outputUrl) {
        String[] commands = new String[14];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = videoUrl;
        commands[3] = "-i";
        commands[4] = musicOrAudio;
        commands[5] = "-filter_complex ";
        commands[6] = "[1:a]aloop=loop=-1:size=2e+09[out];";
        commands[7] = "[out][0:a]amix";
        commands[8] = "-ss";
        commands[9] = String.valueOf(startTime);
        commands[10] = "-t";
        commands[11] = String.valueOf(second);
        commands[12] = "-y";
        commands[13] = outputUrl;
        return commands;
    }

    /**
     * 音频视频合成
     *
     * @param videoUrl     视频路径
     * @param musicOrAudio 音频路径
     * @param videoVolume  视频音量
     * @param audioVolume  音频音量
     * @param outputUrl    输出路径
     * @return ffmpeg -y -i /storage/emulated/0/1/input.mp4 -i /storage/emulated/0/1/input.mp3 -filter_complex [0:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=0.2[a0];[1:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1[a1];[a0][a1]amix=inputs=2:duration=first[aout] -map [aout] -ac 2 -c:v copy -map 0:v:0 -preset superfast /storage/emulated/0/1/result.mp4
     */
    public static String[] composeVideoAudio(String videoUrl, String musicOrAudio, float videoVolume, float audioVolume, String outputUrl) {
        RxFFmpegCommandList commandList = new RxFFmpegCommandList();
        commandList.append("-i");
        commandList.append(videoUrl);
        commandList.append("-i");
        commandList.append(musicOrAudio);
        commandList.append("-filter_complex");
        commandList.append("[0:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=" + String.valueOf((double) (videoVolume / 100d)) + "[a0];[1:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=" + String.valueOf((double) (audioVolume / 100d)) + "[a1];[a0][a1]amix=inputs=2:duration=first[aout]");
        commandList.append("-map");
        commandList.append("[aout]");
        commandList.append("-ac");
        commandList.append("2");
        commandList.append("-c:v");
        commandList.append("copy");
        commandList.append("-map");
        commandList.append("0:v:0");
        commandList.append("-preset");
        commandList.append("superfast");
        commandList.append(outputUrl);
        return commandList.build();
    }

    /**
     * 裁剪视频频
     */
    public static String[] cutTime(String videoUrl, String startTime, String second, String outUrl) {
        String[] commands = new String[10];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = videoUrl;
        commands[3] = "-ss";
        commands[4] = startTime;  //开始裁剪时间
        commands[5] = "-t";
        commands[6] = second;//  视频时长
        commands[7] = "-vcodec";
        commands[8] = "copy";
        commands[9] = outUrl;
        return commands;
    }

    /**
     * 裁剪视频
     */
    public static String[] cutTimeAndVolume(String videoUrl, String startTime, String second, float volume, String outUrl) {
        RxFFmpegCommandList commands = new RxFFmpegCommandList();
        commands.append("-ss");
        commands.append(startTime);  //开始裁剪时间
        commands.append("-i");
        commands.append(videoUrl);
        commands.append("-t");
        commands.append(second);//  视频时长
        commands.append("-vcodec");
        commands.append("copy");
        commands.append("-filter:a");
        commands.append("volume=" + (double) volume / 100d);
        commands.append(outUrl);
        return commands.build();
    }

    /**
     * 使用ffmpeg命令行进行音频剪切
     *
     * @param srcFile    源文件
     * @param startTime  剪切的开始时间(单位为秒)
     * @param duration   剪切时长(单位为秒)
     * @param outputPath 目标文件
     * @return 剪切后的文件
     * "ffmpeg -i %s -ss %d -t %d %s"
     */
    @SuppressLint("DefaultLocale")
    public static String[] cutAudio(String srcFile, int startTime, int duration, String outputPath) {
//        String cutAudioCmd = "ffmpeg -i %s -ss %d -t %d %s";
//        cutAudioCmd = String.format(cutAudioCmd, srcFile, startTime, duration, outputPath);
        String[] commands = new String[10];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = srcFile;
        commands[3] = "-ss";
        commands[4] = String.valueOf(startTime);
        commands[5] = "-t";
        commands[6] = String.valueOf(duration);
        commands[9] = outputPath;
        return commands;//以空格分割为字符串数组
    }

    /**
     * 使用ffmpeg命令行进行音频剪切
     *
     * @param srcFile    源文件
     * @param startTime  剪切的开始时间(单位为秒)
     * @param duration   剪切时长(单位为秒)
     * @param outputPath 目标文件
     * @return 剪切后的文件
     * "ffmpeg -i %s -ss %d -t %d %s"
     */
    @SuppressLint("DefaultLocale")
    public static String[] cutAudio(String srcFile, int startTime, int duration, int volume, String outputPath) {
//        String cutAudioCmd = "ffmpeg -i %s -ss %d -t %d %s";
//        cutAudioCmd = String.format(cutAudioCmd, srcFile, startTime, duration, outputPath);
        String[] commands = new String[10];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = srcFile;
        commands[3] = "-ss";
        commands[4] = String.valueOf(startTime);
        commands[5] = "-t";
        commands[6] = String.valueOf(duration);
        commands[7] = "-filter:a";
        commands[8] = "volume=" + (double) volume / 100d;
//        commands[7] = "-vol";
//        commands[8] = String.valueOf(volume);
        commands[9] = outputPath;
        return commands;//以空格分割为字符串数组
    }

    /**
     * 合并音频视频
     *
     * @param videoFile
     * @param audioFile
     * @param duration
     * @param muxFile
     * @return
     */
    public static String[] mediaMux(String videoFile, String audioFile, int duration, String muxFile) {
        //-t:时长  如果忽略音视频时长，则把"-t %d"去掉
        String mixAudioCmd = "ffmpeg -i %s -i %s -t %d %s";
        mixAudioCmd = String.format(mixAudioCmd, videoFile, audioFile, duration, muxFile);
        return mixAudioCmd.split(" ");//以空格分割为字符串数组
    }

    /**
     * 截取指定时间的一张图
     * ffmpeg -y -i /storage/emulated/0/1/input.mp4 -f image2 -ss 00:00:03 -vframes 1 -preset superfast /storage/emulated/0/1/result.jpg
     */
    public static String[] getImgs(String videoPath, String time, String outPath) {
        RxFFmpegCommandList commandList = new RxFFmpegCommandList();
        commandList.append("-ss");
        commandList.append(time);
        commandList.append("-i");
        commandList.append(videoPath);
        commandList.append("-f");
        commandList.append("image2");
        commandList.append("-vframes");
        commandList.append("1");
        commandList.append("-preset");
        commandList.append("superfast");
        commandList.append(outPath);
        return commandList.build();
    }

    /**
     * 视频添加水印
     * ffmpeg -y -i /storage/emulated/0/1/input.mp4 -i /storage/emulated/0/1/input.png -filter_complex [0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=0:0 -preset superfast /storage/emulated/0/1/result.mp4
     */
    public static String[] addWaterMark(String videoPath, String imgPath, String outPath) {
        RxFFmpegCommandList commandList = new RxFFmpegCommandList();
        commandList.append("-i");
        commandList.append(videoPath);
        commandList.append("-i");
        commandList.append(imgPath);
        commandList.append("-filter_complex");
        commandList.append("[0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=0:0");
        commandList.append("-preset");
        commandList.append("superfast");
        commandList.append(outPath);
        return commandList.build();
    }

    /**
     * get Local video duration
     *
     * @return
     */
    public static int getLocalVideoDuration(String videoPath) {
        int duration;
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata
                    (MediaMetadataRetriever.METADATA_KEY_DURATION));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return duration;
    }

    //优化内存使用
    static StringBuilder mUsDurationText = new StringBuilder();

    /**
     * 微秒转换为 时分秒毫秒,如 00:00:00.000
     *
     * @param us           微秒
     * @param autoEllipsis true:如果小时为0，则这样显示00:00.000;  false:全部显示 00:00:00.000
     * @return
     */
    public static String convertUsToTime(long us, boolean autoEllipsis) {

        mUsDurationText.delete(0, mUsDurationText.length());

        long ms = us / 1000;
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        //天
        long day = ms / dd;
        //小时
        long hour = (ms - day * dd) / hh;
        //分
        long minute = (ms - day * dd - hour * hh) / mi;
        //秒
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        //毫秒
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        if (autoEllipsis) {
            if (hour > 0) {
                mUsDurationText.append(strHour).append(":");
            }
        } else {
            mUsDurationText.append(strHour).append(":");
        }
        mUsDurationText.append(strMinute).append(":")
                .append(strSecond).append(".").append(strMilliSecond);
        return mUsDurationText.toString();
    }

}
