package net.toyproject.mall.restapi.client;

import feign.Request;
import feign.RequestTemplate;
import feign.Target.HardCodedTarget;

public class ApiClientTarget<T> extends HardCodedTarget<T> {

    public ApiClientTarget(Class<T> type, String url) {
        super(type, url);
    }

    @Override
    public Request apply(RequestTemplate input) {
        return super.apply(input);
    }
}
