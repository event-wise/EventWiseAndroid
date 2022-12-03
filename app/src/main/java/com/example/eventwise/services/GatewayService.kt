package com.example.eventwise.services

import com.example.eventwise.models.PasswordChangeRequestModel
import com.example.eventwise.models.ResponseModel
import com.example.eventwise.models.LoginRequestModel
import com.example.eventwise.models.LoginResponseModel
import com.example.eventwise.models.UserModel
import com.example.eventwise.models.RegisterRequestModel
import com.example.eventwise.models.ProfileUpdateRequestModel
import com.example.eventwise.models.EventSaveModel
import com.example.eventwise.models.EventsModel
import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.models.MemberAddRemoveModel
import com.example.eventwise.models.GroupSaveModel
import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.models.GroupsModel
import com.example.eventwise.models.SearchResponseModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Query

interface GatewayService {

    @POST("api/account/change-password")
    suspend fun changePassword(
        @Body passwordChangeRequestModel: PasswordChangeRequestModel
    ): Response<ResponseModel>

    @DELETE("api/account/delete-account")
    suspend fun deleteAccount(): Response<ResponseModel>

    @POST("api/account/login")
    suspend fun authenticateUser(
        @Body loginRequestModel: LoginRequestModel
    ): Response<LoginResponseModel>

    @GET("api/account/logout")
    suspend fun logout(): Response<ResponseModel>

    @GET("api/account/profile")
    suspend fun profile(
        @Query("id") id: Long
    ): Response<UserModel>

    @POST("api/account/register")
    suspend fun register(
        @Body registerRequestModel: RegisterRequestModel
    ): Response<ResponseModel>

    @POST("api/account/update-profile")
    suspend fun updateProfile(
        @Body profileUpdateRequestModel: ProfileUpdateRequestModel
    ): Response<ResponseModel>

    @PUT("api/event/accept-event")
    suspend fun acceptEvent(
        @Query("eventId") eventId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseModel>

    @POST("api/event/create-event")
    suspend fun createEvent(
        @Body eventSaveModel: EventSaveModel
    ): Response<ResponseModel>

    @DELETE("api/event/delete-event")
    suspend fun deleteEvent(
        @Query("eventId") eventId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseModel>

    @GET("api/event/event-details")
    suspend fun getEventDetails(
        @Query("eventId") eventId: Long,
        @Query("userId") userId: Long
    ): Response<EventDetailsModel>

    @GET("api/event/list-user-events")
    suspend fun listUserEvents(
        @Query("userId") userId: Long
    ): Response<List<EventsModel>>

    @PUT("api/event/reject-event")
    suspend fun rejectEvent(
        @Query("eventId") eventId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseModel>

    @POST("api/event/update-event")
    suspend fun updateEvent(
        @Body eventSaveModel: EventSaveModel
    ): Response<ResponseModel>

    @POST("api/group/add-remove-member")
    suspend fun addRemoveMember(
        @Body memberAddRemoveModel: MemberAddRemoveModel
    ): Response<ResponseModel>

    @POST("api/group/create-group")
    suspend fun createGroup(
        @Body groupSaveModel: GroupSaveModel
    ): Response<ResponseModel>

    @DELETE("api/group/delete-group")
    suspend fun deleteGroup(
        @Query("groupId") groupId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseModel>

    @PUT("api/group/exit-from-group")
    suspend fun exitFromGroup(
        @Query("groupId") groupId: Long,
        @Query("userId") userId: Long
    ): Response<ResponseModel>

    @GET("api/group/get-group-details")
    suspend fun getGroupDetails(
        @Query("groupId") groupId: Long,
        @Query("userId") userId: Long
    ): Response<GroupDetailsModel>

    @GET("api/group/list-user-groups")
    suspend fun listUserGroups(
        @Query("userId") userId: Long
    ): Response<List<GroupsModel>>

    @GET("api/group/search-member")
    suspend fun searchMember(
        @Query("groupId") groupId: Long,
        @Query("search") search: String,
        @Query("userId") userId: Long
    ): Response<SearchResponseModel>

    @POST("api/group/update-group")
    suspend fun updateGroup(
        @Body groupSaveModel: GroupSaveModel
    ): Response<ResponseModel>

}
