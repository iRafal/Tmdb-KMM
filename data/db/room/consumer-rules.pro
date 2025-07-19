-keep class * extends androidx.room.RoomDatabase { <init>(); }
-keep,allowobfuscation class com.tmdb.data.db.room.**
-keep,allowobfuscation class com.tmdb.data.db.room.**$* {
    <fields>;
    <init>(...);
}
