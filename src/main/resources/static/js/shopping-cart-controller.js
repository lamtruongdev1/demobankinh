var app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cart = {
        add: function (id) {
            var item = this.items.find(item => item.id == id);

            if (item) {
                item.qty++;
                this.saveToLocalStorage();
                alert("Đã thêm thành công sản phẩm vào giỏ hàng!");
            } else {
                $http.get("/product/" + id).then(function (resp) {
                    resp.data.qty = 1;
                    $scope.cart.items.push(resp.data);
                    $scope.cart.saveToLocalStorage();
                    alert("Đã thêm thành công sản phẩm vào giỏ hàng!");
                });
            }
        },

        remove: function (id) {
            var index = this.items.findIndex(function (item) {
                return item.id == id;
            });

            if (index !== -1) {
                this.items.splice(index, 1);
            }
            $scope.cart.saveToLocalStorage();
        },

        clear: function () {
            this.items = [];
            $scope.cart.saveToLocalStorage();
        },

        amt_of: function (item) {
            return item.qty * item.productPrice;
        },

        updateCartItem: function(item) {
            item.qty = parseInt(item.qty); // Đảm bảo qty là một số nguyên
            item.total = item.qty * item.productPrice;
            this.saveToLocalStorage();
        },

        saveToLocalStorage: function () {
            var json = JSON.stringify(this.items);
            localStorage.setItem("cart", json);
        },

        loadFromLocalStorage: function () {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    };

    // Load cart data from localStorage
    $scope.cart.loadFromLocalStorage();
    $scope.cartLength = $scope.items.length;
});
