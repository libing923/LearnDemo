package subscribe;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import bean.DoubanMovieBean;
import bean.GankResp;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.HttpMethods;
import utils.OnSuccessAndFaultSub;

public class MovieSubscribe extends ViewModel {

    private MutableLiveData<DoubanMovieBean> movieBeanLiveData;

    public MutableLiveData<DoubanMovieBean> getMovieBean() {
        if (movieBeanLiveData == null) {
            movieBeanLiveData = new MutableLiveData<>();
        }
        return movieBeanLiveData;
    }

    private MutableLiveData<GankResp> picLiveData;

    public MutableLiveData<GankResp> getPicData() {
        if (picLiveData == null) {
            picLiveData = new MutableLiveData<>();
        }
        return picLiveData;
    }

    public void getPic(int page, Context context, boolean showProgress) {
        Observable<GankResp> observable = HttpMethods.getInstance().getHttpApi().getPic(page);
        HttpMethods.getInstance().toSubscribe(observable, new OnSuccessAndFaultSub<GankResp>(context, showProgress) {
            @Override
            public void onNext(GankResp gankResp) {
                getPicData().postValue(gankResp);
            }
        });
    }

    public void getTop(int start, int count) {
        Call<DoubanMovieBean> call = HttpMethods.getInstance().getHttpApi().getTop20(start, count);
        call.enqueue(new Callback<DoubanMovieBean>() {
            @Override
            public void onResponse(Call<DoubanMovieBean> call, Response<DoubanMovieBean> response) {
                getMovieBean().postValue(response.body());
            }

            @Override
            public void onFailure(Call<DoubanMovieBean> call, Throwable t) {

            }
        });
    }

    /**
     * 获取数据
     */
    public void getLast(int start, int count, Context context) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("start", start);
//        map.put("count", count);
//        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpApi().getDataForMap(map);
        Observable<DoubanMovieBean> observable = HttpMethods.getInstance().getHttpApi().getTopLast(start, count);
        HttpMethods.getInstance().toSubscribe(observable, new OnSuccessAndFaultSub<DoubanMovieBean>(context) {
            @Override
            public void onNext(DoubanMovieBean doubanMovieBean) {
                getMovieBean().postValue(doubanMovieBean);
            }
        });
    }
}
