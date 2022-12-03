package com.example.eventwise.services

import com.example.eventwise.models.*
import retrofit2.Response
import retrofit2.http.*

interface GatewayService {

    @POST("api/account/change-password")
    suspend fun changePassword(
        @Body passwordChangeRequestModel: PasswordChangeRequestModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @DELETE("api/account/delete-account")
    suspend fun deleteAccount(
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/account/login")
    suspend fun login(
        @Body loginRequestModel: LoginRequestModel
    ): Response<LoginResponseModel>

    @GET("api/account/logout")
    suspend fun logout(
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @GET("api/account/profile")
    suspend fun getProfile(
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<UserModel>

    @POST("api/account/register")
    suspend fun register(
        @Body registerRequestModel: RegisterRequestModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/account/update-profile")
    suspend fun updateProfile(
        @Body profileUpdateRequestModel: ProfileUpdateRequestModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @PUT("api/event/accept-event")
    suspend fun acceptEvent(
        @Query("eventId") eventId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/event/create-event")
    suspend fun createEvent(
        @Body eventSaveModel: EventSaveModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @DELETE("api/event/delete-event")
    suspend fun deleteEvent(
        @Query("eventId") eventId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @GET("api/event/event-details")
    suspend fun getEventDetails(
        @Query("eventId") eventId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<EventDetailsModel>

    @GET("api/event/list-user-events")
    suspend fun listUserEvents(
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<List<EventsModel>>

    @PUT("api/event/reject-event")
    suspend fun rejectEvent(
        @Query("eventId") eventId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/event/update-event")
    suspend fun updateEvent(
        @Body eventSaveModel: EventSaveModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/group/add-remove-member")
    suspend fun addRemoveMember(
        @Body memberAddRemoveModel: MemberAddRemoveModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @POST("api/group/create-group")
    suspend fun createGroup(
        @Body groupSaveModel: GroupSaveModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @DELETE("api/group/delete-group")
    suspend fun deleteGroup(
        @Query("groupId") groupId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @PUT("api/group/exit-from-group")
    suspend fun exitFromGroup(
        @Query("groupId") groupId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

    @GET("api/group/get-group-details")
    suspend fun getGroupDetails(
        @Query("groupId") groupId: Long,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<GroupDetailsModel>

    @GET("api/group/list-user-groups")
    suspend fun listUserGroups(
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<List<GroupsModel>>

    @GET("api/group/search-member")
    suspend fun searchMember(
        @Query("groupId") groupId: Long,
        @Query("search") search: String,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<SearchResponseModel>

    @POST("api/group/update-group")
    suspend fun updateGroup(
        @Body groupSaveModel: GroupSaveModel,
        @Header("Authorization") bearerToken: String = Constants.BEARER_TOKEN
    ): Response<ResponseModel>

}
