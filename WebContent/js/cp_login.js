angular.module('loginApp', [])
  .controller('loginCtr', ['$scope','$rootScope','$timeout',function($scope,$rootScope,$timeout) {
    $scope.tip = ''; 
    $scope.email = '';
    $scope.psd = '';
    $scope.verification = '';
    $scope.clearicon = false;
    $scope.check = function(){
        if($scope.email===''){
            $scope.tip='*请输入邮箱！';
        }else if($scope.psd===''){
            $scope.tip='*请输入密码！';
        }else if($scope.verification===''){
            $scope.tip='*请输入验证码！';
        }
        timeout4();
    }
    $scope.clear = function(){
        $scope.email = '';
    }
    $scope.$watch('email',function(){
        if($scope.email!==''){
            $scope.clearicon = true;
        }else {
            $scope.clearicon = false;
        }
    });

    function timeout4() {      //延迟4s执行
        $timeout(function(){
            $scope.tip='';
        },4000);
    }
  }]);