package cn.edu;

import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class JmuTest {

@Test
    public void add() {
    String priKey = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100a182a3b66cfc0f2284869b1cf9ffd8f4c943ac50213c4d9a8bdff78f99b98915e7c9e685cfb821b4a70874adc644ac9662e60b74cba1239ff35ed22d89aa995069f0df4384b1e254c43cac4f816b0719402c03464dc6b99631d8d3b4a00a295e2147e4edb8a157a0a8e0cfa348daa8a2177a02efbf77a1468c4fceccb12e22ef0203010001028180609f4e1fa9cd95a459d24963a9705534046016eb0d270d6a22ba07598266cbcb2e12cc754b12661fb439cbcbd62c696fc294cb4fc12e92a873294be5f050fe4c77065cf2fb2b6d38cb5aad0bf296b5ea8e6579fba735c0ff140a49d87303fc2ffee2628367d48d5bf51d05b838147e392ee9bff239ee77876fbccf12a398b729024100e6bfb89e1cc30d27a8cfabf4274e2437ea9f625b79b30a89f022f63e502ba5d12246e6f895dcfca24f4347178b2e7e1fe24ddd0adcbf49123732d1c729a5fefd024100b32f3e99b4567ddceda50f839f1942551b1246ffdbc76b22de2faa87149c9404a5d27c98f86e2c0b235e6d686ca83360b29c2e61ece1c5584178975942382b5b02400887f814686dd1b3b0ab14e96ef695a2379b72f07cdc60bc5ed37c152bb3a16acd00a0137abef3dc30600565da707a9b452ba9cb7803de78bcefb50fcefb632502400b0c69b6be88eabfd9a368de7bcbe9ec277c2c0690bdae1c7b48c3220b22507a2ce35aad6ffc131928b824d4e0864a8cb6d6ac817b42bc91619016fcf8f94fab0241008ae4eec1664dff563c8719cbfbd1c5fe5244d033cd725d82078fc3c44f7ee05fce3b5f05b93cac7737fb9a0143b939f32bbdb99031456be52322efa25f9eec0f";
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        String currentTimeStamps = System.currentTimeMillis()+"";
        String sign = "account_name=201672000048&domain=jmu.edu.cn&product=test_jmu_edu_cn&time="+currentTimeStamps;
        String urlParams = "account_name=201672000048&domain=jmu.edu.cn&product=test_jmu_edu_cn&time="+currentTimeStamps;
        System.out.println(sign);
        sign = RSASignatureToQiye.generateSigature(priKey, sign);
        System.out.println(sign);
        urlParams+="&sign="+sign;
        ResponseEntity<String> obj= restTemplate.postForEntity("https://apihz.qiye.163.com/qiyeservice/api/account/getAccount?"+urlParams, null, String.class);
        System.out.println(obj);
    }

}
