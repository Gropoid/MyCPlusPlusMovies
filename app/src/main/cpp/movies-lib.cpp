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
JNIEXPORT jobjectArray JNICALL
Java_fr_geraud_mycplusplusmovies_MoviesControllerWrapper_getMovies
        (JNIEnv *env, jobject obj, jlong handle) {
    movies::MovieController *movieController = reinterpret_cast<movies::MovieController *>(handle);
    auto movies = movieController->getMovies();
    int size = movies.size();
    jclass cls = env->FindClass("fr/geraud/mycplusplusmovies/Movie");
    jmethodID ctor = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;I)V");
    jobjectArray result = env->NewObjectArray(size, cls, NULL);
    for (int i = 0; i < size; i++) {
        jvalue args[2];
        args[0].l = env->NewStringUTF(movies[i]->name.c_str());
        args[1].i = movies[i]->lastUpdated;
        jobject item = env->NewObjectA(cls, ctor, args);
        env->SetObjectArrayElement(result, i, item);
    }
    return result;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_fr_geraud_mycplusplusmovies_MoviesControllerWrapper_getMovieDetail
        (JNIEnv *env, jobject obj, jlong handle, jstring name) {
    movies::MovieController *movieController = reinterpret_cast<movies::MovieController *>(handle);
    std::string name_str = env->GetStringUTFChars(name, NULL);
    movies::MovieDetail *detail = movieController->getMovieDetail(name_str);
    jclass movie_detail_cls = env->FindClass("fr/geraud/mycplusplusmovies/MovieDetail");
    jmethodID movie_detail_ctor = env->GetMethodID(movie_detail_cls,
                                                   "<init>",
                                                   "(Ljava/lang/String;Ljava/lang/String;D[Lfr/geraud/mycplusplusmovies/Actor;)V");
    jclass actor_cls = env->FindClass("fr/geraud/mycplusplusmovies/Actor");
    jmethodID actor_ctor = env->GetMethodID(actor_cls, "<init>",
                                            "(Ljava/lang/String;ILjava/lang/String;)V");
    jvalue movie_detail_args[4];
    movie_detail_args[0].l = env->NewStringUTF(detail->name.c_str());
    movie_detail_args[1].l = env->NewStringUTF(detail->description.c_str());
    movie_detail_args[2].d = detail->score;
    unsigned int actors_size = detail->actors.size();
    jobjectArray actor_array = env->NewObjectArray(actors_size, actor_cls, NULL);
    for (int i = 0; i < actors_size; i++) {
        jvalue actor_args[3];
        actor_args[0].l = env->NewStringUTF(detail->actors[i].name.c_str());
        actor_args[1].i = detail->actors[i].age;
        actor_args[2].l = env->NewStringUTF(detail->actors[i].imageUrl.c_str());
        env->SetObjectArrayElement(actor_array, i,
                                   env->NewObjectA(actor_cls, actor_ctor, actor_args));
    }
    movie_detail_args[3].l = actor_array;
    return env->NewObjectA(movie_detail_cls, movie_detail_ctor, movie_detail_args);
}

#endif //MYCPLUSPLUSMOVIES_MOVIE_CONTROLLER_LIB_H
