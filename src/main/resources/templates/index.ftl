<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>云盘搜索</title>

    <link href="public/css/site.css" rel="stylesheet">
    <link href="public/css/SearchArea.css" rel="stylesheet">
    <link href="public/css/Search.AppList.css" rel="stylesheet">
    <link href="public/css/Header.css" rel="stylesheet">
    <link href="public/css/Search.Options.css" rel="stylesheet">
    <link href="public/css/search.css" rel="stylesheet">
    <link href="public/css/SearchResultItem.css" rel="stylesheet">
    <link href="public/css/CustomDateOption.css" rel="stylesheet">
    <link href="public/css/font-awesome.min.css" rel="stylesheet">
    <link href="public/css/pikaday.css" rel="stylesheet">
    <link rel="stylesheet" href="public/css/font_1476601022_2729867.css">
</head>
<body>
<header>
<#-- <div class="sitenav">
        <a href="">首页</a>
    </div>
    <div class="user-info">
        <a href="" class="user-alias">Gin.p</a>
        <div class="ul">
            <a href=""></a>
        </div>
    </div>-->
</header>
<div class="body">
    <div class="searcher">
        <div class="logo">
        <#--<a href="https://zzk.cnblogs.com/my">
                <img src="./Selenium - 博客园找找看_files/logo.png">
            </a>-->
        </div>
        <div class="searcharea">

            <form action="search" autocomplete="off" method="get">
                <div class="inputarea">
                    <input id="Keywords" name="keyword" type="text" value="${keyword!}">
                    <input type="submit" id="submit" value=" ">
                </div>
            </form>
        </div>

        <div class="applist">
            <a class="selected " href="javascript:void(0);">全部</a>
            <#--<a class="" href="http://zzk.cnblogs.com/my/s/question-p?Keywords=Selenium">我的博问</a>
            <a class="" href="http://zzk.cnblogs.com/my/s/ing-p?Keywords=Selenium">我的闪存</a>
            <a href="http://zzk.cnblogs.com/Private/SearchAllSite?App=blogpost-p&amp;Keywords=Selenium">全站搜</a>-->
        </div>
    </div>

    <#if yunDataList??>
    <div class="results">
        <div class="result-head">
            <div class="options">
                <#--<div class="option" data-dropdown="type">
                    <div class="option-value" href="#">类型 : 全部<span class="caret"></span></div>
                    <div data-dropdown-list="type" class="option-list">
                        <span class="current option-list-item">全部</span>
                        <a class="option-list-item"
                           href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;BlogPostType=1">随笔</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;BlogPostType=2">文章</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;BlogPostType=128">日记</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;BlogPostType=0">其他</a>
                    </div>
                </div>-->

                <div class="option" data-dropdown="date">
                    <div class="option-value" href="#">时间 : 不限<span class="caret"></span></div>
                    <#--<div class="option-list">
                        <span class="current option-list-item">全部</span>
                        <a class="option-list-item"
                           href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;DateTimeRange=OneWeek">一周内</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;DateTimeRange=OneMonth">一月内</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;DateTimeRange=ThreeMonth">三月内</a><a
                            class="option-list-item"
                            href="http://zzk.cnblogs.com/my/s/blogpost-p?Keywords=Selenium&amp;DateTimeRange=OneYear">一年内</a>
                        <div class="option-list-item costom-date">
                            <form action="http://zzk.cnblogs.com/my/s/blogpost-p" autocomplete="off" method="get"><input
                                    id="Keywords" name="Keywords" type="hidden" value="Selenium"><input
                                    id="DateTimeRange" name="DateTimeRange" type="hidden" value="Customer">
                                <div>自定义</div>
                                <div>
                                    从: <input name="datemin" class="date date-from">
                                </div>
                                <div>
                                    至: <input name="datemax" class="date date-to">
                                </div>
                                <div>
                                    <input type="submit" value="确 定" class="submit">
                                </div>
                            </form>
                        </div>
                    </div>-->
                </div>
            </div>
            <#if count??>
            <div class="statistics">
                <span>找到</span>
                ${count}
            <span> 个结果</span>
            </div>
            </#if>
        </div>

        <#list yunDataList as data>
            <div class="result-item">
                <div style="float: left;">
                    <a target="_blank" href="http://pan.baidu.com/share/home?uk=${data.uk?c}#category/type=0">
                        <img width="70" height="70" class="pfs"
                             src="${data.avatarUrl}" alt="" align="Top">
                    </a>
                </div>
                <div class="result-title" style=" padding-left: 10px;width: 80%">
                    <a target="_blank" href="http://pan.baidu.com/share/link?uk=${data.uk?c}&third=0&shareid=${data.shareId?c}&adapt=pc&fr=ftw">
                    ${data.shareName}
                    </a>
                </div>
            <#--<a target="_blank" class="result-url" href="http://www.cnblogs.com/ginponson/articles/5093243.html">
                http://www.cnblogs.com/ginponson/articles/5093243.html
            </a>-->
                <div class="result-content">
                ${data.description!""}
                </div>
                <div class="result-widget">
                <span>
                    <i class="iconfont icon-shijian" aria-hidden="true"></i>
                <#-- 2016-1-1-->
                ${data.shareTime?string("yyyy-MM-dd")}
                </span>
                <#--<span><i class="iconfont icon-dianzan" aria-hidden="true"></i>0</span>
                <span><i class="iconfont icon-pinglun" aria-hidden="true"></i>0</span>
                <span><i class="iconfont icon-liulan" aria-hidden="true"></i>2</span>-->
                </div>
            </div>
        </#list>

    </div>
    </#if>

    <#if yunDataList?? && yunDataList?size != 0>
    <div id="paging_block">
        <div class="pager">
            <a href="search?keyword=${keyword!}&pageindex=1" class="p_1">1</a>
            <a href="search?keyword=${keyword!}&pageindex=2" class="p_2">2</a>
            <a href="search?keyword=${keyword!}&pageindex=3" class="p_3">3</a>
            <a href="search?keyword=${keyword!}&pageindex=4" class="p_4">4</a>
            <a href="search?keyword=${keyword!}&pageindex=5" class="p_5">5</a>
            <a href="search?keyword=${keyword!}&pageindex=6" class="p_6">6</a>
            <a href="search?keyword=${keyword!}&pageindex=7" class="p_7">7</a>
            <a href="search?keyword=${keyword!}&pageindex=8" class="p_8">8</a>
            <a href="search?keyword=${keyword!}&pageindex=9" class="p_9">9</a>
            <a href="search?keyword=${keyword!}&pageindex=10" class="p_10">10</a>
            <a href="search?keyword=${keyword!}&pageindex=11" class="p_11">11</a>
            ···
            <a href="search?keyword=${keyword!}&pageindex=50" class="p_50">50</a>
            <a href="search?keyword=${keyword!}&pageindex=2">Next &gt;</a>
        </div>
    </div>
    </#if>

</div>
<footer>
<#-- ©2004-2017 <a href="http://www.cnblogs.com/"></a>-->
</footer>
<script src="public/js/menu.js"></script>
<script src="public/js/jquery.js" type="text/javascript"></script>
<script>
    $(function () {
        Dropdown.Initialize();

        <#if pageindex??>
            $(".p_${pageindex}").addClass("current").removeAttr('href')

            <#else >
                $(".p_1" ).addClass("current").removeAttr('href')
        </#if>

    });
</script>
</body>
</html>