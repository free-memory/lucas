/**
 * Created by ken on 15/9/14.
 */

//TODO: 1. 获取文章,并且保存不重复.   2.Log用Log4j, 隐藏一些log.  3. 界面美化: a. list可以显示/隐藏. b.用户登录  4. 自动抓取新的文章.

var app = angular.module('rss', []);
app.controller('TaskCtrl', function ($scope, $http) {
        $scope.styles = {};

        //app.config(function ($httpProvider) {
        //    $httpProvider.defaults.transformRequest = function (data) {
        //        if (data === undefined) {
        //            return data;
        //        }
        //        return $.param(data);
        //    };
        //    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
        //
        //});


        //显示文章正文
        $scope.show = function (link) {
            for (var i in $scope.articles) {
                if ($scope.articles[i].link == link) {
                    $scope.content = $scope.articles[i].article;
                    $scope.title = $scope.articles[i].title;
                    break;
                }
            }
        };

        //初始化数据
        $scope.initPage = function (siteId) {
            $scope.fetcureSites();
            $scope.fectureAritcles(siteId);
        };

        //刷新文章列表
        $scope.fectureAritcles = function (siteId) {
            $http.get("http://localhost:8080/articles?siteId=" + siteId)
                .success(function (response) {
                    $scope.articles = response.data;
                });

            for (var prop in $scope.styles) {
                if ($scope.styles.hasOwnProperty(prop)) {
                    //console.log('key is ' + prop + ' and value is' + $scope.styles[prop]);
                    $scope.styles[prop] = "none";
                }
            }
            $scope.styles[siteId] = "active";
        };

        //初始化site list
        $scope.fetcureSites = function () {
            $http.get("http://localhost:8080/sites")
                .success(function (response) {
                    $scope.sites = response.data;
                });
        };

        $scope.catchAritcles = function () {
            for (var i in $scope.sites) {
                console.log("siteid = " + $scope.sites[i].siteid);
                $http({
                    method: 'POST',
                    url: 'http://localhost:8080/articles',
                    data: $scope.sites[i].siteid,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    transformRequest: function (obj) {
                        var str = [];
                        for (var p in obj)
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        return str.join("&");
                    }
                })
                    .success(function (response) {
                        console.log("response = " + response.data);
                    })
            }
        };
    }
)
;

app.filter('to_trusted', ['$sce', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }]
);