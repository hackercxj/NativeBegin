#include <jni.h>
#include <string>
#include "aes_utils.h"
#include "tools.h"
#include "junk.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_dev_nativebegin_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_dev_nativebegin_MainActivity_method01(JNIEnv *env, jclass clazz, jstring str_) {
    const char *str = env->GetStringUTFChars(str_, JNI_FALSE);
    char *result = AES_128_CBC_PKCS5_Encrypt(str);

    env->ReleaseStringUTFChars(str_, str);

    jstring jResult = getJString(env, result);
    free(result);

    return jResult;
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_dev_nativebegin_MainActivity_method02(JNIEnv *env, jclass clazz, jstring str_) {
    const char *str = env->GetStringUTFChars(str_, JNI_FALSE);
    char *result = AES_128_CBC_PKCS5_Decrypt(str);

    env->ReleaseStringUTFChars(str_, str);

    jstring jResult = getJString(env, result);
    free(result);

    return jResult;
}