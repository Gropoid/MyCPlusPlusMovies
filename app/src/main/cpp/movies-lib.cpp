//
// Created by Geraud BEGUIN on 2020-03-01.
//
#ifndef MYCPLUSPLUSMOVIES_MOVIE_CONTROLLER_LIB_H
#define MYCPLUSPLUSMOVIES_MOVIE_CONTROLLER_LIB_H

#include <jni.h>
#include "movies.cpp"
#include "handle.h"
/* Header for class fr_geraud_mycplusplusmovies_MoviesControllerWrapper */

/*
 * Class:     fr_geraud_mycplusplusmovies_MoviesControllerWrapper
 * Method:    createNativeController
 * Signature: ()J
 */
extern "C"
JNIEXPORT jlong JNICALL
Java_fr_geraud_mycplusplusmovies_MoviesControllerWrapper_createMovieController
        (JNIEnv *env, jobject obj) {
    movies::MovieController *controller = new movies::MovieController();
    return (jlong) controller;
}

extern "C"
JNIEXPORT void JNICALL
Java_fr_geraud_mycplusplusmovies_MoviesControllerWrapper_disposeMovieController
        (JNIEnv *env, jobject obj, jlong handle) {
    movies::MovieController *ptr = reinterpret_cast<movies::MovieController *>(handle);
    delete ptr;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_fr_geraud_mycplusplusmovies_MoviesControllerWrapper_getMovies
        (JNIEnv *env, jobject obj, jlong handle) {
    movies::MovieController *movieController = reinterpret_cast<movies::MovieController *>(handle);
    auto movies = movieController->getMovies();
    movies::Movie *firstMovie = movies[0];
    jclass cls = env->FindClass("fr/geraud/mycplusplusmovies/Movie");
    jmethodID ctor = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;I)V");
    jvalue args[2];
    args[0].l = env->NewStringUTF(firstMovie->name.c_str());
    args[1].i = firstMovie->lastUpdated;
    jobject result = env->NewObjectA(cls, ctor, args);
    return result;
}

#endif //MYCPLUSPLUSMOVIES_MOVIE_CONTROLLER_LIB_H
