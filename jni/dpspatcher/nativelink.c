/* 
	Native JNI linkage
						*/
#include <jni.h>
#include <stdlib.h>

extern int dpspatch(int argc, char **argv);

int Java_com_xperia64_rompatcher_MainActivity_dpsPatchRom(JNIEnv * env, jobject this, jstring romPath, jstring patchPath, jstring outFile)
{
	jboolean isCopy;
	char** filez;
	filez = malloc(sizeof(char*) * 4);
	char * command = "dpspatch"; // Arbitrary
	const char * szRomPath = (*env)->GetStringUTFChars(env, romPath, &isCopy);
	const char * szOutFile = (*env)->GetStringUTFChars(env, outFile, &isCopy);
	const char * szPatchPath = (*env)->GetStringUTFChars(env, patchPath, &isCopy);
	filez[0]=command;
	filez[1]=(char*)szRomPath;
	filez[2]=(char*)szOutFile;
	filez[3]=(char*)szPatchPath;
	int err=0;
	err=dpspatch(4, filez);
	(*env)->ReleaseStringUTFChars(env, romPath, szRomPath); 
	(*env)->ReleaseStringUTFChars(env, outFile, szOutFile); 
	(*env)->ReleaseStringUTFChars(env, patchPath, szPatchPath);
	free(filez);
	return err;
}