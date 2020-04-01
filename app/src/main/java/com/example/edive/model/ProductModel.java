package com.example.edive.model;

import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.ICommonModel;
import com.example.edive.frame.ICommonView;
import com.example.edive.frame.NetConfig;
import com.example.edive.frame.NetManager;

import okhttp3.RequestBody;

public class ProductModel implements ICommonModel {

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        NetManager mManager = NetManager.getNetManager();
        switch (whichApi) {
            case ApiConfig.DIBINGCERTIFICATE:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCertifi(),view,whichApi);
                break;
            case ApiConfig.CERTIFICATE_MORES:
                int certificateids = (int) t[0];
                int addressiid = (int) t[1];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCertificateProductS(certificateids,addressiid),view,whichApi);
                break;
            case ApiConfig.CERTIFICATE_MORE:
                int certificateid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCertificateProduct(certificateid),view,whichApi);
                break;
            case ApiConfig.DIVING_DESTILAS:
                int id = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getDetilas(id),view,whichApi);
                break;
            case ApiConfig.ORDER_FRAGMENT:
                int status = (int) t[0];
                int num = (int) t[1];
                int size = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getOrder(status,num,size),view,whichApi);
                break;
            case ApiConfig.ORDER_DETAILS:
                String orderSn = (String) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getOrderDetails(orderSn),view,whichApi);
                break;
            case ApiConfig.CERTIFICATEDETAILS:
                RequestBody body = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCeritDestials(body),view,whichApi);
                break;
            case ApiConfig.COURSEPRODICTORDER:
                RequestBody bodys = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCourseProduct(bodys),view,whichApi);
                break;
            case ApiConfig.SETTLEMPRODU:
                int proid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getSettlem(proid),view,whichApi);
                break;
            case ApiConfig.COMPLETEDORDER:
                String order = (String) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getComPleted(order),view,whichApi);
                break;
            case ApiConfig.EVALUATE:
                RequestBody requestBody = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getEvaluate(requestBody),view,whichApi);
                break;
            case ApiConfig.APPLYREFUND:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getApply(),view,whichApi);
                break;
            case ApiConfig.APPLYTUIKUAN:
                RequestBody bbody = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getApplyTui(bbody),view,whichApi);
                break;
            case ApiConfig.APPLYDEATILS:
                String orderid = (String) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getApplyDeatils(orderid),view,whichApi);
                break;
            case ApiConfig.MYDYNAMICDETAILS:
                String userid = (String) t[0];
                int pageNum = (int) t[1];
                int pageSize = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getMyDynamic(userid,pageNum,pageSize),view,whichApi);
                break;
            case ApiConfig.DELECTMYDYNAMIC:
                int dynamicid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getDelectDynamic(dynamicid),view,whichApi);
                break;
            case ApiConfig.NEWSLIKE:
                int like = (int) t[0];
                int userType = (int) t[1];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getLike(like,userType),view,whichApi);
                break;
            case ApiConfig.NOTLIKE:
                int notlike = (int) t[0];
                int userTypeq = (int) t[1];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getNotLike(notlike,userTypeq),view,whichApi);
                break;
            case ApiConfig.INTEGRAL_ALL:
                int ids = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getIntegralAll(ids),view,whichApi);
                break;
            case ApiConfig.INTEGRALLIST:
                int statuss = (int) t[0];
                int nums = (int) t[1];
                int sizes = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getIntegralList(statuss,nums,sizes),view,whichApi);
                break;
            case ApiConfig.INTEGRALFISHLIST:
                int sstatuss = (int) t[0];
                int snums = (int) t[1];
                int ssizes = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getIntegralFish(sstatuss,snums,ssizes),view,whichApi);
                break;
            case ApiConfig.MYDYNAMICDETAILSS:
                String userids = (String) t[0];
                int pageNums = (int) t[1];
                int pageSizes = (int) t[2];
                int usertypr = (int) t[3];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getMyDynamicS(userids,pageNums,pageSizes,usertypr),view,whichApi);
                break;
        }
    }
}
