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
	size_t i;
	size_t size = num * 1024l * 1024l;

	if(allocated) {
		free(data);
	}
	printf("Int: %lu\n", sizeof(int));
	printf("size_t: %lu\n", sizeof(size_t));
	data = malloc(size);
	if(data) {
		printf("[JNI]: Successfully allocated %d MB\n\n", num);
		int filled = -1;
		size_t multiplier = 10l;
		for(i = 0; i < size; i++) {
			data[i] = 1;
			if(i % (1024l * 1024l * multiplier) == 0) {
				filled++;
				if(filled != 0) {
					printf("[JNI]: Filled %lu MB ...\n", filled * multiplier);
				}
			}
		}
		allocated = 1;
		printf("[JNI]: Successfully filled %lu MB.\n\n", i / (1024l * 1024l));
	} else {
		printf("[JNI]: Failed to allocate %d MB.\n\n", num);
		allocated = 0;
	}
	return allocated;
}