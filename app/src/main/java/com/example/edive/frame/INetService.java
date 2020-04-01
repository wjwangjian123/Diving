package com.example.edive.frame;


import com.example.edive.bean.AddBean;
import com.example.edive.bean.AddCreaterBean;
import com.example.edive.bean.AddDynamicBean;
import com.example.edive.bean.AddressListBean;
import com.example.edive.bean.Addressbean;
import com.example.edive.bean.AlbumBean;
import com.example.edive.bean.AllCommentBean;
import com.example.edive.bean.AllMessagerBean;
import com.example.edive.bean.AllSpeNumBean;
import com.example.edive.bean.ApplyBean;
import com.example.edive.bean.ApplyDeatilsBean;
import com.example.edive.bean.ApplyTuiBean;
import com.example.edive.bean.BannerInfo;
import com.example.edive.bean.CertificateProductBean;
import com.example.edive.bean.CertificcateBean;
import com.example.edive.bean.CertifitDetailsBean;
import com.example.edive.bean.ChuanBean;
import com.example.edive.bean.CommentBean;
import com.example.edive.bean.CompletedBean;
import com.example.edive.bean.ConversationBean;
import com.example.edive.bean.CourseProductBean;
import com.example.edive.bean.CreaterBean;
import com.example.edive.bean.DelectAddressBean;
import com.example.edive.bean.DelectMydynamicBean;
import com.example.edive.bean.DivingBean;
import com.example.edive.bean.DivingDestilasBean;
import com.example.edive.bean.DynamicDetailsBean;
import com.example.edive.bean.EvaluateBean;
import com.example.edive.bean.FindPasswordBean;
import com.example.edive.bean.FollowBean;
import com.example.edive.bean.FollowListBean;
import com.example.edive.bean.FuBean;
import com.example.edive.bean.GuanzhuBean;
import com.example.edive.bean.HotBean;
import com.example.edive.bean.HotDetalisBean;
import com.example.edive.bean.IntegralAllBean;
import com.example.edive.bean.IntegralBean;
import com.example.edive.bean.IntegralDeatlisBean;
import com.example.edive.bean.IntegralGiftsBean;
import com.example.edive.bean.IntegralGiftsBeans;
import com.example.edive.bean.IntegralListBean;
import com.example.edive.bean.IntegralShoppingBean;
import com.example.edive.bean.Integralfishbean;
import com.example.edive.bean.LikeBean;
import com.example.edive.bean.LikeListBean;
import com.example.edive.bean.LocationBean;
import com.example.edive.bean.LoginBean;
import com.example.edive.bean.MoAddress;
import com.example.edive.bean.MyDynamicBean;
import com.example.edive.bean.MyPersonBean;
import com.example.edive.bean.NewDynamicBean;
import com.example.edive.bean.NewsDynamicDeatilsBean;
import com.example.edive.bean.NotFollowBean;
import com.example.edive.bean.OrderBean;
import com.example.edive.bean.OrderDetailsBean;
import com.example.edive.bean.OrdernumBean;
import com.example.edive.bean.PersonBean;
import com.example.edive.bean.ReadBean;
import com.example.edive.bean.RegisterBean;
import com.example.edive.bean.SearchBean;
import com.example.edive.bean.SearchDataBean;
import com.example.edive.bean.SearchDelectBean;
import com.example.edive.bean.SearchDivingBean;
import com.example.edive.bean.SearchDynamicBean;
import com.example.edive.bean.SearchShoppingBean;
import com.example.edive.bean.SearchTopicBean;
import com.example.edive.bean.SelectDynaimcBean;
import com.example.edive.bean.SettlmBean;
import com.example.edive.bean.SuggestBean;
import com.example.edive.bean.SuggestTiBean;
import com.example.edive.bean.TopicBean;
import com.example.edive.bean.TopicByIdBean;
import com.example.edive.bean.UpdataPasswordBean;
import com.example.edive.bean.UpdatePhoneBean;
import com.example.edive.bean.UserDatilasBean;
import com.example.edive.bean.VerificationBean;
import com.example.edive.bean.VerificationLgoinBean;
import com.example.edive.bean.YiduBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface INetService {
    @POST("shop/shopAdvertise")
    Observable<BannerInfo> getFuData(@Body RequestBody body);
    @GET
    Observable<FuBean> getFuDatas(@Url String url);
    @POST("sso/login?")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<LoginBean> getPasswordLogin(@Field("grant_type")String type,@Field("username")String username,@Field("password")String password);
    @GET("integralGifts/selectIntegralGiftsPage?")
    Observable<IntegralBean> getFuDatass(@Query("pageNum") int num,@Query("pageSize") int size);

    @GET("sso/getAuthCode?")
    @Headers("deviceId:007")
    Observable<VerificationBean> getVerGetcode(@Query("mobile") String mobile);
    @POST("sso/mobile?")
    @FormUrlEncoded
    @Headers("Authorization:Basic ZG1kOjEyMzQ1Ng==")
    Observable<VerificationLgoinBean> getVerLogin(@Field("code")String code, @Field("mobile") String mobile);
    @POST("sso/register?")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("username") String username, @Field("password") String password, @Field("authCode") String authCode, @Field("invitationCode") String invitationCode);
    @POST("sso/findPassword")
    Observable<FindPasswordBean> getFindPassword(@Body RequestBody body);
    @GET("dynamic/selectDynamicHeat")
    Observable<HotBean> getHotData(@Query("pageNum") int num,@Query("pageSize") int pagesize);
    @GET("dynamic/selectDynamicTime")
    Observable<NewDynamicBean> getNewsDynamicData(@Query("pageNum") int num,@Query("pageSize") int pagesize);
    @GET("topic/selectTopic")
    Observable<TopicBean> getHomeTopic();
    @GET("topic/selectTopicByDynamicTime")
    Observable<NewsDynamicDeatilsBean> getNewsDynamicDetalisData(@Query("id") int id,@Query("pageNum") int num,@Query("pageSize") int size);
    @GET("topic/selectTopicByDynamicHeat")
    Observable<HotDetalisBean> getHotDetalisData(@Query("pageNum") int num,@Query("pageSize") int size,@Query("id") int idss);
    @GET("integralGifts/selectIntegralGiftsById?")
    Observable<IntegralDeatlisBean> getIntegralDeatlis(@Query("id") int integralid);
    @GET("topic/selectTopicPage?")
    Observable<ConversationBean> getConverSation(@Query("pageNum") int pagenumc,@Query("pageSize") int pagesizec);
    @GET("dynamic/selectDynamicById?")
    Observable<DynamicDetailsBean> getDynamicdatas(@Query("id") int post,@Query("userType") int userTypes);
    @GET("search/searchAndAddContent")
    Observable<SearchBean> getSearchData(@Query("content") String str,@Query("userId") String userId,@Query("searchType") int searchType,@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);
    @GET("search/queryHistory?")
    Observable<SearchDataBean> getSearchListoy(@Query("userId") String ididid);
    @GET("topic/selectTopicById?")
    Observable<TopicByIdBean> getTopicDtaaa(@Query("id") int topicid);
    @GET("dynamic/updateDynamicLikePraise?")
    Observable<LikeBean> getLike(@Query("id") int like,@Query("userType") int userType);
    @GET("dynamic/updateDynamicCancelPraise?")
    Observable<LikeBean> getNotLike(@Query("id") int notlike,@Query("userType") int userType);
    @POST("comment/addComment?")
    Observable<CommentBean> getComment(@Body RequestBody bodys);
    @GET("comment/selectCommentAll?")
    Observable<AllCommentBean> getAllComment(@Query("pageNum") int commentnum,@Query("pageSize") int commentsize,@Query("forDynamicId") int commentid);
    @POST("umsFavorites/attention/save?")
    Observable<FollowBean> getFollow(@Body RequestBody follow);
    @GET("umsFavorites/favoritesList/find?")
    Observable<FollowListBean> getFollowList(@Query("pageNum") int num,@Query("pageSize") int siaze,@Query("favoriteType") int type);
    @POST("umsFavorites/attentionStatus/update?")
    Observable<NotFollowBean> getNotfollow(@Body RequestBody bodyfollow);
    @POST("pms/divingProductList/find")
    Observable<DivingBean> getDiving(@Body RequestBody ha);
    @GET("pms/certificateList/find")
    Observable<CertificcateBean> getCreate();
    @GET("pmsPlayAddress/playAddress/findAll")
    Observable<LocationBean> getCertifi();
    @GET("pms/certificateProduct/find")
    Observable<CertificateProductBean> getCertificateProduct(@Query("certificateId") int certificateid);
    @GET("pms/courseProduct")
    Observable<DivingDestilasBean> getDetilas(@Query("id") int id);
    @GET("oms/userOrderList/findByStatus")
    Observable<OrderBean> getOrder(@Query("status") int status,@Query("pageNum") int num,@Query("pageSize") int size);
    @GET("oms/userOrderDetail/query")
    Observable<OrderDetailsBean> getOrderDetails(@Query("orderSn") String orderSn);
    @POST("pms/certificateProductDetail/find")
    Observable<CertifitDetailsBean> getCeritDestials(@Body RequestBody body);
    @POST("oms/courseProductOrder/create")
    Observable<CourseProductBean> getCourseProduct(@Body RequestBody bodys);
    @GET("pms/courseProduct/settlement")
    Observable<SettlmBean> getSettlem(@Query("productId") int proid);
    @GET("dynamic/selectDynamicCount")
    Observable<SelectDynaimcBean> getSelect(@Query("userId") String id);
    @POST("authenticationed/member/updatePassword")
    Observable<UpdataPasswordBean> getUpdatePassword(@Body RequestBody body);
    @GET("search/searchcontent")
    Observable<SearchBean> getSearchLianx(@Query("content") String contens,@Query("userId") String userids,@Query("searchType") int types,@Query("pageNum") int nums,@Query("pageSize") int sizes);
    @GET("search/deleteHistoryByUserid")
    Observable<SearchDelectBean> getDelectSearch(@Query("userId") String userid);
    @GET("oms/confirmationCompleted/order")
    Observable<CompletedBean> getComPleted(@Query("orderSn") String order);
    @POST("appraise/appraise/order")
    Observable<EvaluateBean> getEvaluate(@Body RequestBody requestBody);

    @GET("topic/selectTopicName")
    Observable<SearchDynamicBean> getSearchDynaic(@Query("topicName") String data);
    @GET("umsFavorites/selectFavoritesCount")
    Observable<GuanzhuBean> getGuanzhu(@Query("userId") int userid);
    @GET("omsOrderReturnReason/returnReason/query")
    Observable<ApplyBean> getApply();
    @POST("omsOrderReturnApply/orderReturn/apply")
    Observable<ApplyTuiBean> getApplyTui(@Body RequestBody bbody);
    @GET("omsOrderReturnApply/returnApplyDetail/find")
    Observable<ApplyDeatilsBean> getApplyDeatils(@Query("orderSn") String orderid);
    @POST("umsFavorites/attentionStatus/update?")
    Observable<NotFollowBean> getNotfollows(@Body RequestBody bodyfollow);
    @GET("myIntegral/selectMyIntegral")
    Observable<IntegralAllBean> getIntegralAll(@Query("userId") int ids);
    @GET("search/searchAndAddContent")
    Observable<SearchShoppingBean> getSearchDatas(@Query("content") String str, @Query("userId") String userId, @Query("searchType") int searchType, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
    @GET("search/searchAndAddContent")
    Observable<SearchTopicBean> getSearchDatass(@Query("content") String str, @Query("userId") String userId, @Query("searchType") int searchType, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
    @GET("integralGifts/selectIntegralGiftsSpeById")
    Observable<IntegralGiftsBean> getIntegralFints(@Query("id") int inte);
    @GET("integralGifts/selectIntegralGiftsSpecStock")
    Observable<IntegralGiftsBeans> getIntegralGift(@Query("id") int giftid, @Query("size") String siz);
    @POST("dynamic/addDynamic")
    Observable<AddDynamicBean> getAddDynamic(@Body RequestBody requestBody);
    @GET("dynamic/selectDynamicPage")
    Observable<MyDynamicBean> getMyDynamic(@Query("userId") String userid,@Query("pageNum") int pageNum,@Query("pageSize") int pageSize);
    @GET("dynamic/updateDynamicDelflag")
    Observable<DelectMydynamicBean> getDelectDynamic(@Query("id") int dynamicid);
    @POST("authenticationed/member/updatePhone")
    @FormUrlEncoded
    Observable<UpdatePhoneBean> getUpdatePhone(@Field("telephone") String phone,@Field("authCode") String code);
    @POST("omsShipping/addShipping")
    Observable<Addressbean> getAddaddress(@Body RequestBody requestBodys);
    @POST("omsShipping/queryUserShippingListWithPage")
    Observable<AddressListBean> getAddressList(@Body RequestBody body);
    @GET("omsShipping/deleteShipping")
    Observable<DelectAddressBean> getDelectAddress(@Query("shippingId") int addressid);
    @POST("omsShipping/selectShippingById")
    Observable<MoAddress> getMOren();
    @POST("oms/IntegralOrder/create")
    Observable<ChuanBean> getChuan(@Body RequestBody requestBodyss);
    @POST("authenticationed/member/memberMessage/find")
    Observable<MyPersonBean> getMyPersonal();
    @POST("authenticationed/member/updatePersonalData")
    Observable<PersonBean> getpeson(@Body RequestBody requestBody);
    @GET("omsShipping/setDefaultAddress")
    Observable<AddBean> getAddaress(@Query("shippingId") int addaressid);
    @GET("integralGifts/integralProduct/settlement")
    Observable<IntegralShoppingBean> getJiesuan(@Query("productSkuId") int pid,@Query("productQuantity") int pronum);
    @GET("ums/message/find")
    Observable<LikeListBean> getLikeList(@Query("pageNum") int num,@Query("pageSize") int size,@Query("messageType") int types);
    @GET("umsNoticeMark/noticeMark/updateIsRead")
    Observable<YiduBean> getyidu(@Query("noticeId") int notid);
    @POST("umsNoticeMark/isRead/find")
    Observable<ReadBean> getRead();
    @GET("feedback/selectProblemFeedback")
    Observable<SuggestBean> getSuggers();
    @POST("feedback/addFeedback")
    Observable<SuggestTiBean> getAddSug(@Body RequestBody vodys);
    @GET("search/searchAndAddContent")
    Observable<SearchDivingBean> getSearchDatassss(@Query("content") String str, @Query("userId") String userId, @Query("searchType") int searchType, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
    @POST("omsShipping/updateShipping")
    Observable<Addressbean> getUpdateAddaress(@Body RequestBody vodyss);
    @GET("diveCertificate/selectDiveCertificate")
    Observable<CreaterBean> getCreater(@Query("userId") int idid);
    @POST("diveCertificate/addDiveCertificate")
    Observable<AddCreaterBean> getAddCreater(@Body RequestBody bodys);

    @POST("umsFavorites/attention/save?")
    Observable<FollowBean> getFollows(@Body RequestBody follow);
    @GET("oms/integralOrderList/findByStatus")
    Observable<IntegralListBean> getIntegralList(@Query("status") int statuss,@Query("pageNum") int nums,@Query("pageSize") int sizes);
    @GET("oms/integralOrderList/findByStatus")
    Observable<Integralfishbean> getIntegralFish(@Query("status") int sstatuss,@Query("pageNum") int snums,@Query("pageSize") int ssizes);
    @GET("appraise/appraiseMessage/findList")
    Observable<AllMessagerBean> getMessager(@Query("productId") int prid,@Query("pageNum") int nump,@Query("pageSize") int sizep);
    @GET("dynamic/queryUserDetails")
    Observable<UserDatilasBean> getUserPerson(@Query("userId") int userids,@Query("userType") int userType);
    @POST("oms/orderNum/count")
    Observable<OrdernumBean> getOrdernum();
    @GET("dynamic/selectDynamicAlbumTimeBean")
    Observable<AlbumBean> getAlbum(@Query("userId") int useridperson,@Query("pageNum") int personnum,@Query("pageSize") int personsize,@Query("userType") int userTypes);

    @GET("pms/certificateProduct/find")
    Observable<CertificateProductBean> getCertificateProductS(@Query("certificateId") int certificateid,@Query("addressId") int addressiid);
    @GET("dynamic/selectDynamicPage")
    Observable<MyDynamicBean> getMyDynamicS(@Query("userId") String userid,@Query("pageNum") int pageNum,@Query("pageSize") int pageSize,@Query("userType") int usertype);
    @GET("integralGifts/selectIntegralGiftsSpeNum")
    Observable<AllSpeNumBean> getAllSpenum(@Query("id") int pids);
}
