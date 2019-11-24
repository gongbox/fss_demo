package com.fss.demo.route;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.fss.router.FssRouteApi;
import com.fss.adapter.listview.CommonAdapter;
import com.fss.bind.annotation.BindActivity;
import com.fss.bind.annotation.BindView;
import com.fss.demo.R;
import com.fss.demo.base.BaseActivity;
import com.fss.demo.route.extra.ParcelableType;
import com.fss.demo.route.extra.SerializableType;
import com.fss.demo.util.ToastUtils;
import com.fss.router.annotation.Route;
import com.fss.router.api.callback.OnActivityResult;
import com.fss.router.api.launcher.FssRouter;

import java.util.Arrays;
import java.util.List;

@Route
@BindActivity(value = R.layout.activity_list_view, finishViewId = R.id.img_back)
public class RouteMainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    @butterknife.BindView(R.id.list_view)
    private ListView listView;

    private List<String> datas = Arrays.asList(
            "路由到详情页",
            "隐式意图",
            "路由参数测试",
            "默认参数路由测试",
            "SERVICE路由测试",
            "获取返回路由测试",
            "子模块路由测试"
    );

    protected void initView() {
        listView.setAdapter(new CommonAdapter<>(this, datas, R.layout.layout_list_item,
                (holder, s, position) ->
                        holder.setText(R.id.tv_text, s)));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                FssRouteApi.DETAIL.navigateToRouteDetailActivity(this, "123");
                break;
            case 1:
                FssRouteApi.DEFAULT.navigateToRouteActionActivity(this);
                break;
            case 2:
                FssRouteApi.DEFAULT.navigateToRouteExtraTestActivity(this,
                        true, (byte) 2, 'c', (short) 12,
                        13, 14L, 1.1f, 1.3, "hello",
                        new SerializableType("a", 12),
                        new ParcelableType("a", 12)
                );
                break;
            case 3:
                FssRouteApi.DEFAULT.navigateToRouteExtraTestActivity(this,
                        new SerializableType("a", 12),
                        new ParcelableType("a", 12));
                break;
            case 4:
                FssRouteApi.TEST.navigateToRouteTestService(this, "12465");
                break;
            case 5:
                FssRouteApi.DEFAULT.navigateToRouteCallbackActivity(this, new OnActivityResult() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                        ToastUtils.showToast("requestCode:" + requestCode + ",resultCode:" + resultCode);
                    }
                });
                break;
            case 6:
                FssRouteApi
//                com.fss.demo.submoudle.FssRouteApi.DEFAULT.navigateToMoudleTestActivity(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FssRouter.getInstance().onActivityResult(requestCode, resultCode, data);
    }
}
