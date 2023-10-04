##---------------Begin: proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}


##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
@retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-dontwarn kotlinx.**


##---------------Begin: proguard configuration for Glide ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
<init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
*** rewind();
}

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


##---------------Begin: proguard configuration for RxJava ----------
# Uncomment if you use RxJava
#-dontwarn java.util.concurrent.Flow*

-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Keep the class with a no-args constructor
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.GithubUserResponse {
  public <init>();
}

# Keep all fields in GithubUserResponse
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.GithubUserResponse {
  <fields>;
}

# Keep all public methods in GithubUserResponse
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.GithubUserResponse {
  public *;
}

# Keep the class with a no-args constructor
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.Items {
  public <init>();
}

# Keep all fields in the Items class
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.Items {
  <fields>;
}

# Keep all public methods in the Items class
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.Items {
  public *;
}

# Keep the class with a no-args constructor
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse {
  public <init>();
}

# Keep all fields in the DetailResponse class
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse {
  <fields>;
}

# Keep all public methods in the DetailResponse class
-keepclassmembers class com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse {
  public *;
}

# Keep Kotlin standard library classes
-keep class kotlin.** { *; }

# Keep kotlinx.coroutines classes (if your project uses coroutines)
-keep class kotlinx.coroutines.** { *; }

# Keep any other Kotlin-related classes you may be using
# -keep class com.example.myapp.** { *; }

# Keep ViewBinding classes
-keep class androidx.viewbinding.** { *; }

# Keep the ViewBinding class fields
-keepclassmembers class * {
    androidx.viewbinding.ViewBinding *;
}

# Keep resource IDs used by ViewBinding
-keepclassmembers class **.R$* {
    public static <fields>;
}
