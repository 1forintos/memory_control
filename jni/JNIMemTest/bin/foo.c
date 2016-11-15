#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include "JNITest.h"

char *data;
int allocated = 0;


JNIEXPORT void JNICALL Java_JNITest_freeMemory(JNIEnv *env, jobject obj) {
	if(allocated) {
		free(data);
		allocated = 0;
	}
}


JNIEXPORT jint JNICALL Java_JNITest_allocateAndFillMemory(JNIEnv *env, jobject obj, jint num) {
	int i;
	if(allocated) {
		free(data);
	}
	data = malloc(num * 1024 * 1024);
	if(data) {
		int filled = 0;
		int multiplier = 10;
		for(i = 0; i < num * 1024 * 1024; i++) {
			data[i] = 0;
			if(i % (1024 * 1024 * multiplier)) {
				filled++;
				printf("[JNI]: Filled %d MB ...\n", filled * multiplier)
			}
		}
		allocated = 1;
		printf("[JNI]: Successfully allocated and filled %d MB.\n", num);
	} else {
		printf("[JNI]: Failed to allocate %d MB.\n", num);
		allocated = 0;
	}
	return allocated;
}