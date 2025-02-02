package com.example.lab9mysql_updatedelete

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentAPI {
    @GET("allStd")
    fun retrieveStudent(): Call<List<Student>>

    @FormUrlEncoded
    @POST("std")
    fun insertStd(
        @Field("std_id") std_id: String,
        @Field("std_name") std_name: String,
        @Field("std_gender") std_gender: String,
        @Field("std_age") std_age: Int
    ): Call<Student>

    /*@GET("std/{std_id}")
    fun retrieveStudentID(
        @Path("std_id") std_id: String
    ): Call<Student>
*/
    @FormUrlEncoded
    @PUT("std/{std_id}")
    fun updateStudent(
        @Path("std_id") std_id: String,
        @Field("std_name") std_name: String,
        @Field("std_gender") std_gender: String,
        @Field("std_age") std_age: Int
    ): Call<Student>

    @DELETE("std/{std_id}")
    fun deleteStudent(
        @Path("std_id") std_id: String
    ): Call<Student>

    companion object {
        fun create(): StudentAPI {
            val stdClient: StudentAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentAPI::class.java)
            return stdClient
        }
    }
}