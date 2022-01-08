package com.hzdl.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hzdl.edg.domain.Evidence;
import com.hzdl.edg.domain.block.CommonResponse;
import com.hzdl.edg.domain.block.EvidenceRequest;
import com.hzdl.edg.domain.block.EvidenceResponse;
import com.hzdl.system.util.ConfigUtil;
import org.springframework.util.Assert;
import java.util.HashMap;
import java.util.List;

import static com.hzdl.constant.EvidenceConstant.evidenceServer;
import static com.hzdl.constant.EvidenceConstant.fileServer;

/**
 * 存证工具类
 *
 * @author FY
 * @date 2020/9/9
 */
public class EvidenceUtil {

    private static final String SERVER = "10.112.56.65:3000";//ConfigUtil.getConfigByKey("edg.evidence.server");
    public static final String KEY_ID_SEPARATOR = "|";
    private static final String FILE_SERVER = "10.112.56.65:3080"; //ConfigUtil.getConfigByKey("edg.file.server");

    /**
     * 存证
     *
     * @param request 存证数据
     * @return 存证结果
     * @author FY
     * @date 2020/9/9
     */
    public static EvidenceResponse doEvidence(EvidenceRequest request) {
        String result = HttpUtil.post(SERVER + "/storeReceipt", JSON.toJSONString(request));
        return JSON.parseObject(result, EvidenceResponse.class);
    }

    /**
     * 校验文件
     *
     * @param keyId    区块链上的id
     * @param fileHash 文件哈希
     * @return 是否通过校验
     * @author FY
     * @date 2020/9/9
     */
    public static CommonResponse verify(String keyId, String fileHash) {
        String result = HttpUtil.post(SERVER + "/verifyFile", JSON.toJSONString(new HashMap<String, Object>(2) {
            {
                put("keyId", keyId);
                put("fileHash", fileHash);
            }
        }));
        return JSON.parseObject(result, CommonResponse.class);
    }

    /**
     * 单链溯源
     *
     * @param keyIds 多个keyId，按照根节点->叶节点的顺序排列
     * @return 是否通过校验
     * @author FY
     * @date 2020/9/9
     */
    public static CommonResponse verifyChain(List<String> keyIds) {
        String result = HttpUtil.post(SERVER + "/verifyChain", JSON.toJSONString(new HashMap<String, Object>(1) {
            {
                put("keyId", keyIds);
            }
        }));
        return JSON.parseObject(result, CommonResponse.class);
    }

    /**
     * 获取文件服务器中的文件总大小
     *
     * @return 文件总大小，单位GB
     * @author FY
     * @date 2020/09/15
     */
    public static double fileSize() {
        String result = HttpUtil.get(FILE_SERVER + "/group1/status");
        JSONArray jsonArray = JSON.parseObject(result).getJSONObject("data").getJSONArray("Fs.FileStats");
        double totalSize = jsonArray.getJSONObject(jsonArray.size() - 1).getDoubleValue("totalSize");
        return totalSize / Math.pow(1024, 3);
    }


    /**
     * 树形结构溯源
     *
     * @param list 多条链的keyId集合
     * @return 溯源结果
     * @author FY
     * @date 2020/09/14
     */
    public static CommonResponse verifyTree(List<List<String>> list) {
        String result = HttpUtil.post(SERVER + "/verifyTree", JSON.toJSONString(new HashMap<String, Object>(1) {
            {
                put("chainSet", list);
            }
        }));
        return JSON.parseObject(result, CommonResponse.class);
    }
}
