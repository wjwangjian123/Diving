package com.example.edive.model;

import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.ICommonModel;
import com.example.edive.frame.ICommonView;
import com.example.edive.frame.NetConfig;
import com.example.edive.frame.NetManager;

import okhttp3.RequestBody;

public class PersonModel implements ICommonModel {
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        NetManager mManager = NetManager.getNetManager();
        switch (whichApi) {
            case ApiConfig.FOLLOWLIST:
                int numa = (int) t[0];
                int sizea = (int) t[1];
                int type = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getFollowList(numa,sizea,type),view,whichApi);
                break;
            case ApiConfig.NOTFOLLOW:
                RequestBody bodyfollow = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getNotfollow(bodyfollow),view,whichApi);
                break;
            case ApiConfig.MYSELECTDYNAMICCOUNT:
                String id = (String) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getSelect(id),view,whichApi);
                break;
            case ApiConfig.GUANZHUZONGSHU:
                int userid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getGuanzhu(userid),view,whichApi);
                break;
            case ApiConfig.INTEGRAL_ALL:
                int ids = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getIntegralAll(ids),view,whichApi);
                break;
            case ApiConfig.UPDATEPHONE:
                String phone = (String) t[0];
                String code = (String) t[1];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getUpdatePhone(phone,code),view,whichApi);
                break;
            case ApiConfig.ADDRESSLIST:
                RequestBody body = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getAddressList(body),view,whichApi);
                break;
            case ApiConfig.DELECTADDRESS:
                int addressid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getDelectAddress(addressid),view,whichApi);
                break;
            case ApiConfig.MYPERSONDESTIALS:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getMyPersonal(),view,whichApi);
                break;
            case ApiConfig.XIUGAIGERENXX:
                RequestBody requestBody = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getpeson(requestBody),view,whichApi);
                break;
            case ApiConfig.ADDARESSMOREN:
                int addaressid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getAddaress(addaressid),view,whichApi);
                break;
            case ApiConfig.LIKELIST:
                int num = (int) t[0];
                int size = (int) t[1];
                int types = (int) t[2];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getLikeList(num,size,types),view,whichApi);
                break;
            case ApiConfig.BIAOJIYIDU:
                int notid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getyidu(notid),view,whichApi);
                break;
            case ApiConfig.CHAXUNSHIF:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getRead(),view,whichApi);
                break;
            case ApiConfig.SUGGEST:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getSuggers(),view,whichApi);
                break;
            case ApiConfig.TIJIAOYIJIAN:
                RequestBody vodys = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getAddSug(vodys),view,whichApi);
                break;
            case ApiConfig.EDITADDARESS:
                RequestBody vodyss = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getUpdateAddaress(vodyss),view,whichApi);
                break;
            case ApiConfig.CREATERIFICATE:
                int idid = (int) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getCreater(idid),view,whichApi);
                break;
            case ApiConfig.ADDCREATER:
                RequestBody bodys = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getAddCreater(bodys),view,whichApi);
                break;
            case ApiConfig.FOLLOWUSERS:
                RequestBody follow = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getFollows(follow),view,whichApi);
                break;
            case ApiConfig.USERDATALIS:
                int userids = (int) t[0];
                int userType = (int) t[1];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getUserPerson(userids,userType),view,whichApi);
                break;
            case ApiConfig.NOTFOLLOWS:
                RequestBody bodyfollows = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getNotfollows(bodyfollows),view,whichApi);
                break;
            case ApiConfig.FOLLOWUSER:
                RequestBody follows = (RequestBody) t[0];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getFollow(follows),view,whichApi);
                break;
            case ApiConfig.ORDERNUM:
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getOrdernum(),view,whichApi);
                break;
            case ApiConfig.LABUMUSERPERSONAL:
                int useridperson = (int) t[0];
                int personnum = (int) t[1];
                int personsize = (int) t[2];
                int userTypes = (int) t[3];
                mManager.method(mManager.getNetService(NetConfig.BASE_URL2).getAlbum(useridperson,personnum,personsize,userTypes),view,whichApi);
                break;
        }
    }
}
