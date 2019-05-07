# Ijkplayer
较为清洁的Ijkplayer工程，用于实现自己的播放器

支持协议： http,https,rtmp

支持视频格式: MP4,MPG,MKV,TS

暂不支持视频格式：AVI,WMV,VOB,RMVB,RM


### 修改配置
```
cd /config/
rm module.sh
ln -s module-lite.sh module.sh
## 如果做了以上修改配置操作，那么在compile-openssl 时需要先执行 clean操作
```

### Build Android
```
./init-android.sh

cd android/contrib
./compile-ffmpeg.sh clean
./compile-ffmpeg.sh all

cd ..
./compile-ijk.sh all
```