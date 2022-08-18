//package com.truing.onebox.home.filter;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.util.BooleanUtil;
//import cn.hutool.core.util.StrUtil;
//import com.truing.onebox.common.utils.StringUtils;
//import org.springframework.http.HttpHeaders;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @ClassName DownloadLinkFilter
// * @Description TODO
// * @Author Colin
// * @Date 2022/8/15 15:53
// * @Version 1.0
// */
//@WebFilter(urlPatterns = "/*")
//public class DownloadLinkFilter implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//        String requestUrl = httpServletRequest.getRequestURI();
//
//        // 获取路径层级，少于 2 层的不可能是直链
//        List<String> list = StrUtil.split(requestUrl, '/', true, true);
//        if (CollUtil.size(list) > 2) {
//            // 获取当前请求 URL 的前缀
//            String currentRequestPrefix = list.get(0);
//            // 获取存储源 key
//            String currentStorageKey = list.get(1);
//
//            // 获取下载文件路径
//            List<String> pathList = CollUtil.sub(list, 2, list.size());
//            String filePath = CollUtil.join(pathList, StringUtils.DELIMITER_STR);
//
//            // 获取系统配置的直链前缀
//            SystemConfigDTO systemConfig = systemConfigService.getSystemConfig();
//            String directLinkPrefix = systemConfig.getDirectLinkPrefix();
//            if (StrUtil.equalsIgnoreCase(currentRequestPrefix, directLinkPrefix)) {
//
//                if (BooleanUtil.isFalse(systemConfig.getShowPathLink())) {
//                    httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=utf-8");
//                    httpServletResponse.getWriter().write("当前系统不允许使用直链.");
//                    return;
//                }
//
//                // 获取下载地址全路径, 不以 / 开头的要补 /, 进行了 url encode 编码的要进行解码
//                String decodeFilePath = StringUtils.concat(ZFileConstant.PATH_SEPARATOR, StringUtils.decode(filePath));
//
//                // 根据存储源 key 获取存储源 id.
//                Integer storageId = storageSourceService.findIdByKey(currentStorageKey);
//                if (filterConfigService.filterResultIsDisableDownload(storageId, decodeFilePath)) {
//                    // 获取 Forbidden 页面地址
//                    String forbiddenUrl = systemConfigService.getForbiddenUrl();
//                    httpServletResponse.sendRedirect(forbiddenUrl);
//                    return;
//                }
//                handleDownloadLink(httpServletResponse, storageId, currentStorageKey, decodeFilePath);
//                return;
//            }
//        }
//        chain.doFilter(httpServletRequest, httpServletResponse);
//    }
//
//    private void handleDownloadLink(HttpServletResponse response, Integer storageId, String storageKey, String filePath) throws IOException {
//        StorageSource storageSource = storageSourceService.findByStorageKey(storageKey);
//        Boolean enable = storageSource.getEnable();
//        if (!enable) {
//            log.error("存储源当前状态为未启用，仍然请求下载，存储源 key {}, 文件路径 {}", storageKey, filePath);
//            response.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=utf-8");
//            response.getWriter().write("当前存储源未启用, 无法下载，请联系管理员!");
//            return;
//        }
//
//        if (filePath.length() > 0 && filePath.charAt(0) != ZFileConstant.PATH_SEPARATOR_CHAR) {
//            filePath = "/" + filePath;
//        }
//
//        ShortLink shortLink = shortLinkService.findByStorageIdAndUrl(storageId, filePath);
//        // 如果没有短链，则生成短链
//        if (shortLink == null) {
//            shortLink = shortLinkService.generatorShortLink(storageId, filePath);
//        }
//
//        shortLinkService.handlerDownload(storageKey, filePath, shortLink.getShortKey());
//    }
//
//}
