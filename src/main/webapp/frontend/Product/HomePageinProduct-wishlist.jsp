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
            <div class="box-container">
                <div class="product-name" style="font-size: large; text-align: center;">
                    我的最愛列表
                </div>
            </div>
                        <div class="container" id="app">
                <!-- Cart Table -->
                <div class="table-content table-responsive cart-table-content">
                    <table class="table table-bordered align-middle">
                        <thead>
                            <tr class="text-uppercase text-nowrap">
                                <th style="width: 80px;" class="text-dark fw-500"></th>
                                <th class="text-dark fw-500">遊戲名稱</th>
                                <th style="width: 120px;" class="text-dark fw-500">價錢</th>
                                <!-- <th style="width: 120px;" class="text-dark fw-500">Qty</th>
                                <th style="width: 120px;" class="text-dark fw-500">Subtotal</th> -->
                                <th style="width: 230px;" class="text-dark text-end fw-500 ">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in jsonData" :key="index">
                                <td class="product-thumbnail">
                                    <a class="text-reset"
                                        :href="`/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=`+item.productNo"><img
                                            :src=item.picList[0].imageUrl class="img-fluid" width="60" alt=""></a>
                                </td>
                                <td class="product-name"><a class="text-reset"
                                        :href="`/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=`+item.productNo">{{item.productName}}</a>
                                </td>
                                <td class="product-price-cart"><span class="amount">{{item.productPrice}}</span></td>
                                <td class="product-remove text-end text-nowrap">
                                    <a :href="`/CGA101G1/product/deleteMemFavor?ProductNo=`+item.productNo"
                                        class="btn btn-sm btn-outline-dark text-nowrap px-3"><i
                                            class="bi bi-x lh-1"></i></a>
                                    <button @click="webcamSendRequestButton($event)" class="btn"
                                        :value="item.productNo+a+item.productPrice+a+item.productName"
                                        id="liveToastBtn">
                                        <i class="bi bi-cart"></i>
                                        加入購物車
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
                    </div>
                    <!-- End Content -->
                </div>
            </div>
        </div>
        <!--Table -->
        <!-- 跳出加入購物車的視窗 -->
        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="liveToast1" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <img src="../mainCss/images/logo.png" class="rounded me-2" alt="..."
                        style="width: 10%; height: 10%;">
                    <strong class="me-auto">系統訊息</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body" style="background-color: #7d879c;;">
                    <h6 style="color: aliceblue;">商品成功加入購物車!!</h6>
                </div>
            </div>
        </div>
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
        const App = {
            data() {
                return {
                    // jsonUrl: 'https://api.kcg.gov.tw/api/service/Get/9c8e1450-e833-499c-8320-29b36b7ace5c',
                    jsonData: [], // 初始資料
                    picData: [],
                    a: ";",
                    // renderData: [], // 建立每頁顯示資料
                    // pageBaseNum: 6,  // 每頁顯示資料數量
                    // nowPage: 0, // 初始、現在頁數
                }
            },
            methods: {
                // renderingPage() {
                //     for (let n = 0; n < this.totalPage; n++) { // 依每頁顯示數量建立新陣列
                //         this.renderData.push(
                //             (this.jsonData.slice
                //                 (n * this.pageBaseNum, n * this.pageBaseNum + this.pageBaseNum)
                //             )
                //         );
                //     }
                // },
                getProductData() {
                    // const apiUrl = 'https://api.kcg.gov.tw/api/service/Get/9c8e1450-e833-499c-8320-29b36b7ace5c';
                    const apiUrl = '/CGA101G1/product/ShowMemFavoritelist';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductData的狀態：" + response.status); //狀態碼
                            // console.log(response.data);
                            // console.log(response.data.data.XML_Head.Infos.Info);
                            this.jsonData = response.data;//塞進去
                            console.log("datastore讀取資料ok");
                            this.getPicData();
                            this.renderingPage();
                        });
                },
                getPicData() {
                    const apiUrl = '/CGA101G1/product/GetAllCovers';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取pic的狀態：" + response.status); //狀態碼

                            this.picData = response.data;//塞進去
                            this.insertPic(this.picData);

                        });
                },
                insertPic(picData) {
                    console.log("insertPic開始");
                    for (j = 0; j < this.jsonData.length; j++) {
                        this.jsonData[j].picList = [];
                        for (i = 0; i < picData.length; i++) {
                            if (picData[i].productNo == this.jsonData[j].productNo) {
                                this.jsonData[j].picList.push(picData[i]);
                            }
                        }
                    }
                    this.picData = [];
                    console.log("insert完畢");
                },
                webcamSendRequestButton(e) {
                	 let toastLiveExample1 = document.getElementById('liveToast1');
                    // 取編號跟錢，分離商品編號跟價錢再傳出去

                    proNo = e.target.value.split(';')[0];

                    proPrice = e.target.value.split(';')[1];

                    proName = e.target.value.split(';')[2];
                    sendURL = `/CGA101G1/product/add2ShoppingCart?ProductNo=` + proNo + `&ProductSales=1&ProductTotalPrice=` + proPrice + `&ProductName=` + proName;

                    axios({
                        method: 'post',
                        url: sendURL,
                    })
                        .then((response) => {
                        	console.log(response);
                            let toast = new bootstrap.Toast(toastLiveExample1)
                            toast.show()
                        	});
                    
                }

                // previousPage() {
                //     if (this.nowPage != 0) { // 頁數初始值為 0 
                //         this.nowPage = this.nowPage - 1;  // 當頁數不是初始值，減一頁(回上一頁)
                //     }
                // },
                // nextPage() {
                //     if (this.nowPage < this.totalPage - 1) {
                //         this.nowPage++;  // 當頁數小於總頁數，加一頁(下一頁)
                //     }
                // },
                // skipPage(pageNum) {
                //     this.nowPage = pageNum; // 將資料換成點擊的頁碼，切換頁面
                // }
            },
            // computed: {
            //     totalPage() {
            //         return Math.ceil(this.jsonData.length / this.pageBaseNum); // 依每頁數量計算總頁數
            //     }
            // },
            created() {
                this.getProductData();
            }
        };

        /* app.component('card-template', {
             props: ['readData', 'nowPage'],
             template: `
     <div class="col-sm-6 col-lg-4" v-for="(item, index) in readData[nowPage]" :key="index" style="height:60em">
         <div class="product-card-1" style="height:90%">
             <div class="product-card-image">
                                         <div class="badge-ribbon">
                                             <span class="badge bg-danger">Sale</span>
                                         </div>
                                         <div class="product-action">
                                             <a :href="`+ "`" + `/HomeTest/AddProduct2Fav?ProductNo=` + "`" + "+" + "item.ProductNo" + `" class="btn btn-outline-primary">
                                                 <i class="bi bi-heart"></i>
                                             </a>
                                             <a href="shop-compare.html" class="btn btn-outline-primary">
                                                 <i class="bi bi-arrow-left-right"></i>
                                             </a>
                                             <a data-bs-toggle="modal" data-bs-target="#px-quick-view"
                                                 href="javascript:void(0)" class="btn btn-outline-primary">
                                                 <i class="bi bi-eye-fill"></i>
                                             </a>
                                         </div>
                                         <div class="product-media"> 
                                             <a :href="`+ "`" + `/HomeTest/frontend/product/HomePageinProduct.html?ProductNo=` + "`" + "+" + "item.ProductNo" + `">
                                                 <img class="img-fluid"
                                                 :src=item.imageUrl  style="height: 80%;">
                                             </a>
                                             <div class="product-cart-btn">
                                                 <a href="shopping-cart.html" class="btn btn-primary btn-sm w-100">
                                                     <i class="bi bi-cart"></i>
                                                     Add to cart
                                                 </a>
                                             </div>
                                         </div>
                                     </div>
                                     <div class="product-card-info">
                                         <div class="rating-star text">
                                             <i class="bi bi-star-fill active"></i>
                                             <i class="bi bi-star-fill active"></i>
                                             <i class="bi bi-star-fill active"></i>
                                             <i class="bi bi-star-fill active"></i>
                                             <i class="bi bi-star"></i>
                                         </div>
                                         <h6 class="product-title">
                                             <a href="shop-fw-without-filtres.html">{{item.ProductName}}</a>
                                         </h6>
                                         <div class="product-price">
                                             <span class="text-primary">{{item.ProductPrice}}</span>
                                             
                                         </div>
 
                                     </div>
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
 */
        Vue.createApp(App).mount('#app');



    </script>
</body>
<!-- end body -->

</html>
<%@include file="/frontend/frontfoot.jsp" %>