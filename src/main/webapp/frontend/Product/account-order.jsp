<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/frontend/fronthead.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <!-- Title of The Page -->
    <title>Fancy Shopping E-commerce HTML Template</title>
    <!-- Meta Informations -->
    <meta charset="utf-8">
    <meta name="description" content="Bootstrap Responsive  Fancy Shopping E-commerce HTML Template">
    <meta name="keywords"
        content="Fancy Shopping E-commerce HTML Template,template,bootstrap 5,ui template kit,envato templates,fancy shopping html templates,html,css">
    <meta name="author" content="Fancy Shopping E-commerce HTML Template">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">




    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/3.0.2/vue.global.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <!-- font awesome cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- custom css file link -->
    <link rel="stylesheet" href="../mainCss/css/allStyle.css">
    <link rel="stylesheet" href="../mainCss/css/style.css">
    <link rel="stylesheet" href="../mainCss/css/headerStyle.css">
    <link rel="stylesheet" href="../mainCss/css/footerStyle.css">


    <!-- Favicon -->
    <link rel="shortcut icon" href="/CGA101G1/frontend/mainCss/images/logo.png">
    <!-- Fonts CSS -->
    <link rel="stylesheet" href="../mainCss/assets/vendor/fonts/fonts.css">
    <!-- Bootstrap-icons CSS -->
    <link rel="stylesheet" href="../mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
    <!--Magnific-Popup CSS -->
    <link rel="stylesheet" href="../mainCss/assets/vendor/magnific/magnific-popup.css">
    <!-- Slick CSS -->
    <link rel="stylesheet" href="../mainCss/assets/vendor/slick/slick.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="../mainCss/assets/css/style01.css">
</head>

<body>

        <!-- End Breadcrumb -->
        <!-- Table -->

                    <!-- Profile Menu -->
                    
                    <!-- End Profile Menu -->
                    <!-- Content -->
                    <div class="col-lg-8 col-xxl-9" id="app">
                        <h5 style="padding-left: 9%;">訂單搜尋</h5>
                        <div class="shop-sidebar-block" style="width: 30%; margin-bottom: 0%;margin-left: 9%;">
                            <input class="form-control" type="text" name="keysearch" id="" @change="keysearch($event)"
                                placeholder="請輸入訂單號碼或是遊戲名稱">
                        </div>

                        <section class="row" id="content" name="content" v-bind:dataDetails="datastore"
                            style="background-color: white;">
                            <card-template :read-data="renderData" :now-page="nowPage"></card-template>
                        </section>

                        <nav class="d-flex justify-content-center">
                            <pagination-list :pages="renderData" :now-page="nowPage" :total-page="totalPage"
                                @skip-page="skipPage" @previous-page="previousPage" @next-page="nextPage">
                            </pagination-list>
                        </nav>
                    </div>
                    <!-- End Content -->
                </div>
            </div>
        </div>
        <!--Table -->
    </main>
    <!-- End Main -->

    <!-- jquery -->
    <script src="../mainCss/assets/js/jquery-3.5.1.min.js"></script>
    <!-- appear -->
    <script src="../mainCss/assets/vendor/appear/jquery.appear.js"></script>
    <!--bootstrap-->
    <script src="../mainCss/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- owl-carousel -->
    <script src="../mainCss/assets/vendor/slick/slick.min.js"></script>
    <!-- magnific -->
    <script src="../mainCss/assets/vendor/magnific/jquery.magnific-popup.min.js"></script>
    <!-- isotope -->
    <script src="../mainCss/assets/vendor/isotope/isotope.pkgd.min.js"></script>
    <!-- count-down -->
    <script src="../mainCss/assets/vendor/count-down/jquery.countdown.min.js"></script>
    <!-- Theme Js -->
    <script src="../mainCss/assets/js/custom.js"></script>





    <script>
        //custom select box

        // $(function () {
        //     $('select.styled').customSelect();
        // });
        const app = Vue.createApp({
            data() {
                return {
                    datastore: [],
                    temp: [],
                    picData: [],
                    gametype: [],
                    gameplatform: [],
                    ProductName: [],
                    //驗證表單用




                    renderData: [], // 建立每頁顯示資料
                    pageBaseNum: 8,  // 每頁顯示資料數量
                    nowPage: 0, // 初始、現在頁數

                }
            },
            methods: {
                renderingPage() {
                    this.renderData = [];
                    for (let n = 0; n < this.totalPage; n++) { // 依每頁顯示數量建立新陣列
                        this.renderData.push(
                            (this.temp.slice
                                (n * this.pageBaseNum, n * this.pageBaseNum + this.pageBaseNum)
                            )
                        );
                    }
                },
                getOrderData() {
                    const apiUrl = '/CGA101G1/product/showAllOrderAndDetailsByMemNo';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductData的狀態：" + response.status); //狀態碼
                            this.datastore = response.data;//塞進去
                            console.log("datastore讀取資料ok");
                            this.getProductName();
                            console.log("開始加遊戲名稱");
                            for (e of this.datastore) {
                                this.temp.push(e);
                            }
                            this.renderingPage();



                        });
                },
                getProductName() {
                    const apiUrl = '/CGA101G1/product/showAllProductsName';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductName的狀態：" + response.status); //狀態碼
                            this.ProductName = response.data;//塞進去
                            console.log("ProductName讀取資料ok");


                            for (i = 0; i < this.datastore.length; i++) {
                                // console.log("遊戲名稱:" + this.datastore[i].orderDetail[0].productNo);
                                for (j = 0; j < this.datastore[i].orderDetail.length; j++) {
                                    // console.log("這是明細的遊戲編號:" + this.datastore[i].orderDetail[j].productNo);
                                    this.datastore[i].orderDetail[j].productName = '';
                                    // console.log(this.datastore[i].orderDetail[j].productName);
                                    //名字放不進去
                                    for (k = 0; k < this.ProductName.length; k++) {
                                        // console.log("這是遊戲名稱的遊戲編號" + this.ProductName[k].productNo);
                                        if (this.datastore[i].orderDetail[j].productNo == this.ProductName[k].productNo) {
                                            // console.log("開始換遊戲名稱")
                                            this.datastore[i].orderDetail[j].productName = this.ProductName[k].productName;
                                        }
                                    }

                                }
                            }



                        });
                },
                keysearch(e) {
                    console.log(e.target.value);
                    console.log(Boolean(e.target.value));
                    this.temp = [];
                    if (e.target.value) {
                        this.datastore.filter(item => {
                            for (i = 0; i < item.orderDetail.length; i++) {
                                if (item.orderDetail[i].productName.indexOf(e.target.value) !== -1) {
                                    this.temp.push(item);
                                }
                            }
                            if (item.orderNo.indexOf(e.target.value) !== -1) {
                                this.temp.push(item);
                            }
                        })
                    } else {
                        this.datastore.filter(item => {
                            if (item.orderNo.indexOf(e.target.value) !== -1) {
                                this.temp.push(item);
                            }
                        })
                    };
                    this.renderingPage();

                },




                previousPage() {
                    if (this.nowPage != 0) { // 頁數初始值為 0 
                        this.nowPage = this.nowPage - 1;  // 當頁數不是初始值，減一頁(回上一頁)
                    }
                },
                nextPage() {
                    if (this.nowPage < this.totalPage - 1) {
                        this.nowPage++;  // 當頁數小於總頁數，加一頁(下一頁)
                    }
                },
                skipPage(pageNum) {
                    this.nowPage = pageNum; // 將資料換成點擊的頁碼，切換頁面
                },




                //table的方法


            },
            computed: {
                totalPage() {
                    return Math.ceil(this.temp.length / this.pageBaseNum); // 依每頁數量計算總頁數
                }
            },
            created() {
                this.getOrderData();

            }
        })

        app.component('card-template', {
            data() {
                return {

                }
            },
            props: ['readData', 'nowPage', 'dataDetails'],
            methods: {
                openOrderDetails(e) {



                    //取編號
                    // console.log(e.target.value);
                    // console.log(this.readData[0][0].openOrderDetail);

                    for (i = 0; i < this.readData[0].length; i++) {
                        if (e.target.value == this.readData[0][i].orderNo) {
                            this.readData[0][i].openOrderDetail = !this.readData[0][i].openOrderDetail;
                        }
                    }
                },
            },
            template: ` <div v-for="(item, index) in  readData[nowPage]" :key="index" class="table-responsive fs-md mb-4">
                            <table class="table table-bordered table-hover mb-0">
                                <thead class="text-700 bg-gray-200">
                                    <tr>
                                        <th class="fw-600" style="width:25%">訂單編號：點擊查看明細</th>
                                        <th class="fw-600" style="width:25%">下單日期</th>
                                        <th class="fw-600" style="width:25%">訂單狀態</th>
                                        <th class="fw-600" style="width:25%">金額</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="p-3"><button class="page-link" data-bs-toggle="modal"
                                                @click="openOrderDetails($event)"
                                                :value="item.orderNo">{{item.orderNo}}</button>
                                        </td>
                                        <td class="p-3">{{item.tranTime}}</td>
                                        <td class="p-3"><span class="badge bg-info m-0">{{item.orderStateStr}}</span>
                                        </td>
                                        <td class="p-3">NT:{{item.orderTotalPrice}}</td>
                                    </tr>
                                </tbody>
                            </table>
                            
                            <div v-show="item.openOrderDetail" style="width: 63%;">
                                <table class="table table-bordered table-hover mb-0">
                                    <thead class="text-700 bg-gray-200">
                                        <tr>
                                            
                                        <th class="fw-600" style="width:40%">遊戲名稱</th>
                                        <th class="fw-600" style="width:15%">數量</th>
                                        <th class="fw-600" style="width:15%">總價</th>
                                        <th class="fw-600" style="width:30%">發表評論</th>
                                        </tr>
                                    </thead>
                                    <tbody v-for="(orderDetails, index) in item.orderDetail" :key="index">
                                        <tr>
                                            
                                            <td class="p-3">{{orderDetails.productName}}</td>
                                            <td class="p-3">{{orderDetails.productSales}}</td>
                                            <td class="p-3"><span
                                                    class="badge bg-info m-0">{{orderDetails.productTotalPrice}}</span>
                                            </td>
                                            
                                            <td class="p-3">
                                            
                                                <a v-show="(item.orderState == 2 && orderDetails.CommentStar == 0 ? true:false)" :href="`+ "`" + `/CGA101G1/frontend/Product/postComment.html?ProductNo=` + "`" + "+" + "orderDetails.productNo" + "+" + "`" + `&OrderNo=` + "`" + "+" + "orderDetails.orderNo" + `">我要發表遊戲評論</a>
    
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
        `,
        })

        app.component('pagination-list', {
            props: ['pages', 'nowPage', 'totalPage'],
            methods: {
                previousPage() {  // 下一頁
                    this.$emit('previousPage');
                },
                nextPage() {  // 上一頁
                    this.$emit('nextPage');
                },
                skipPage(pageNum) { // 跳頁
                    this.$emit('skipPage', pageNum);
                },
            },
            template: `
        <ul class="pagination" id="pageid">

        <!-- <li class="page-item" v-if="nowPage > 0" @click.prevent="previousPage"><a class="page-link" href="#">Previous</a></li> -->

        <li class="page-item" :class="{'disabled' : nowPage === 0 }" @click.prevent="previousPage"><a class="page-link" href="#">Previous</a></li>

        <li class="page-item" :class="{'active' : nowPage === index}" v-for="(item, index) in pages" :key="index + 1" @click.prevent="skipPage(index)"><a class="page-link" href="#" data-page="{{index + 1}}">{{index + 1}}</a></li>

        <li class="page-item" :class="{'disabled' : nowPage === totalPage - 1}" @click.prevent="nextPage"><a class="page-link" href="#">Next</a></li>

        <!-- <li class="page-item" v-if="nowPage < totalPage - 1" @click.prevent="nextPage"><a class="page-link" href="#">Next</a></li> -->

        </ul>
        `,
        })
        app.mount('#app');



    </script>
</body>
<!-- end body -->

</html>
<%@include file="/frontend/frontfoot.jsp" %>