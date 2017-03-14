var app = angular.module("MyApp", [ 'ui.router' ]);

app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider.state("products", {
		url : "/products",
		templateUrl : "views/products.html",
		controller : "ProductController"
	});

	$stateProvider.state("category", {
		url : "/products",
		templateUrl : "views/category.html",
		controller : "CategoryController"
	});

	$stateProvider.state("newproduct", {
		url : "/addnew",
		templateUrl : "views/newproduct.html",
		controller : "NewProductController"
	});
	
	$stateProvider.state("users", {
		url : "/users",
		templateUrl : "views/users.html",
		controller : "ContactController"
	});
	
	
	
	$stateProvider.state("logout", {
		url : "/logout",
		templateUrl : "templates/login.html",
		
	});

	$urlRouterProvider.otherwise("products");

});

app.controller('NewProductController', function($scope, $http) {

	$scope.product = {};
	$scope.mode=0;
	$scope.modeForm=function(){
		$scope.product = {};
		$scope.mode=0;
		
	}
	$scope.saveProduct = function() {

		$http.post('http://localhost:8080/Products', $scope.product)

		.success(function(data) {
			$scope.product = data;
			$scope.mode=1;

		})

		.error(function(err) {

			console.log(err);

		});

	}

});

app.controller("CategoryController", function($scope, $http) {

});

app.controller("ContactController", function($scope, $http) {
	
	var uid = 1;
	$scope.user=null;
	
	
	
	$scope.saveContact=function(){
		$http.post('http://localhost:8080/Users', $scope.user)

		.success(function(data){
			//$scope.contacts = data;
			$scope.getUsers();

		})

		.error(function(err) {

			console.log(err);

		});

	};
	
	
	$scope.getUsers = function(){
		
		$http.get("http://localhost:8080/Users")
		
		.success(function(data){
			$scope.contacts = data;

		}).error(function(err) {
			console.log(err);
		})
	};
		
	
	
	
	
	
	
	
	/*$scope.contacts = [{
	        id: 0,
	        'name': 'Ali',
	            'email': 'hello@gmail.com',
	            'phone': '123-2343-44'
	    }];*/
	
	/*$scope.saveContact=function(newcontact){
		
		newcontact.id = uid++;
		$scope.contacts.push(newcontact);
		$scope.newcontact={};
		
	}*/
	$scope.getUsers();
});
app.controller("ProductController", function($scope, $http) {

	$scope.products = null;
	$scope.keyWord = "";
	$scope.pageProduct = null;
	$scope.pageCount = 0;
	$scope.size = 10;
	$scope.pages = [];
	$scope.load = function() {

		$http.get(
				"http://localhost:8080/Fetch?mc=" + $scope.keyWord + "&page="
						+ $scope.pageCount + "&size=" + $scope.size + "")
				.success(function(data) {
					$scope.pageProduct = data;
					$scope.pages = new Array(data.totalPages);
				}).error(function(err) {
					console.log(err);
				})

	}

	$scope.gotopage = function(p) {

		$scope.pageCount = p;
		$scope.load();
	}

});
