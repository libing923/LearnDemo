package api;

import bean.DouBanMovieRequest;
import bean.DoubanMovieBean;
import bean.GankResp;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpApi {

    @POST()
    Observable<ResponseBody> getDataForBean(@Body DouBanMovieRequest bean);

    @Headers("url_name:girls")
    @GET("{page}")
    Observable<GankResp> getPic(@Path("page") int page);

    @GET()
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    //    获取豆瓣Top250 榜单
    @Headers("url_name:v2")
    @GET("in_theaters")
    Observable<DoubanMovieBean> getTopLast(@Query("start") int start, @Query("count") int count);

    @Headers("url_name:v2")
    @FormUrlEncoded
    @POST("top250")
    Call<DoubanMovieBean> getTop20(@Field("start") int start, @Field("count") int count);

}
