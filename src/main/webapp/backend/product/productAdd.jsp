<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>POP.Game ServerSide</title>

    <!-- Bootstrap core CSS -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="../assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="../assets/css/zabuto_calendar.css">
    <!-- <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" /> -->
    <link rel="stylesheet" type="text/css" href="../assets/lineicons/style.css">

    <!-- Custom styles for this template -->
    <link href="../assets/css/style.css" rel="stylesheet">
    <link href="../assets/css/style-responsive.css" rel="stylesheet">

    <script src="../assets/js/chart-master/Chart.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/3.0.2/vue.global.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

    <section id="container">
        <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
        <!--header start-->
        <header class="header black-bg">
            <div class="sidebar-toggle-box">
                <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
            </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>POP.Game</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <i class="fa fa-tasks"></i>
                            <span class="badge bg-theme">4</span>
                        </a>
                        <ul class="dropdown-menu extended tasks-bar">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">You have 4 pending tasks</p>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="task-info">
                                        <div class="desc">DashGum Admin Panel</div>
                                        <div class="percent">40%</div>
                                    </div>
                                    <div class="progress progress-striped">
                                        <div class="progress-bar progress-bar-success" role="progressbar"
                                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="task-info">
                                        <div class="desc">Database Update</div>
                                        <div class="percent">60%</div>
                                    </div>
                                    <div class="progress progress-striped">
                                        <div class="progress-bar progress-bar-warning" role="progressbar"
                                            aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="task-info">
                                        <div class="desc">Product Development</div>
                                        <div class="percent">80%</div>
                                    </div>
                                    <div class="progress progress-striped">
                                        <div class="progress-bar progress-bar-info" role="progressbar"
                                            aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="task-info">
                                        <div class="desc">Payments Sent</div>
                                        <div class="percent">70%</div>
                                    </div>
                                    <div class="progress progress-striped">
                                        <div class="progress-bar progress-bar-danger" role="progressbar"
                                            aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                                            <span class="sr-only">70% Complete (Important)</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="external">
                                <a href="#">See All Tasks</a>
                            </li>
                        </ul>
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <i class="fa fa-envelope-o"></i>
                            <span class="badge bg-theme">5</span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">You have 5 new messages</p>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <span class="photo"><img alt="avatar" src="assets/img/ui-zac.jpg"></span>
                                    <span class="subject">
                                        <span class="from">Zac Snider</span>
                                        <span class="time">Just now</span>
                                    </span>
                                    <span class="message">
                                        Hi mate, how is everything?
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <span class="photo"><img alt="avatar" src="assets/img/ui-divya.jpg"></span>
                                    <span class="subject">
                                        <span class="from">Divya Manian</span>
                                        <span class="time">40 mins.</span>
                                    </span>
                                    <span class="message">
                                        Hi, I need your help with this.
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <span class="photo"><img alt="avatar" src="assets/img/ui-danro.jpg"></span>
                                    <span class="subject">
                                        <span class="from">Dan Rogers</span>
                                        <span class="time">2 hrs.</span>
                                    </span>
                                    <span class="message">
                                        Love your new Dashboard.
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <span class="photo"><img alt="avatar" src="assets/img/ui-sherman.jpg"></span>
                                    <span class="subject">
                                        <span class="from">Dj Sherman</span>
                                        <span class="time">4 hrs.</span>
                                    </span>
                                    <span class="message">
                                        Please, answer asap.
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">See all messages</a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-menu">
                <ul class="nav pull-right top-menu">
                    <li><a class="logout" href="login.html">Logout</a></li>
                </ul>
            </div>
        </header>
        <!--header end-->

        <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
        <!--sidebar start-->
        <aside>
            <div id="sidebar" class="nav-collapse ">
                <!-- sidebar menu start-->
                <ul class="sidebar-menu" id="nav-accordion">

                    <p class="centered"><a href="profile.html"><img src="assets/img/logo.png" class="img-circle"
                                width="60"></a></p>
                    <h5 class="centered">侯宇陽</h5>

                    <li class="mt">
                        <a class="active" href="index.html">
                            <i class="fa fa-dashboard"></i>
                            <span>首頁</span>
                        </a>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-desktop"></i>
                            <span>使用者管理</span>
                        </a>
                        <ul class="sub">
                            <li><a href=" blank.html">管理員資料管理</a></li>
                            <li><a href=" blank.html">管理員權限管理</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>主要商城後臺系統</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">商品管理</a></li>
                            <li><a href="#">主要訂單管理</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>競標管理系統</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">二手遊戲驗收管理</a></li>
                            <li><a href="#">查看競標商品</a></li>
                            <li><a href="#">競標訂單管理</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>前台網站管理系統</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">會員資料管理</a></li>
                            <li><a href="#">討論區檢舉管理</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>討論區管理系統</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">討論區權限編輯</a></li>
                            <li><a href="#">新增或關閉討論區</a></li>
                            <li><a href="#">發表管理員文章</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>FQ管理系統</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">真人文字客服</a></li>
                            <li><a href="#">關鍵字設定</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>遊戲平台及種類管理</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">遊戲平台管理</a></li>
                            <li><a href="#">遊戲種類管理</a></li>
                        </ul>
                    </li>

                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>行銷管理</span>
                        </a>
                        <ul class="sub">
                            <li><a href="#">編輯新聞</a></li>
                            <li><a href="#">優惠券管理</a></li>
                        </ul>
                    </li>

                    <!-- <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-tasks"></i>
                            <span>Forms</span>
                        </a>
                        <ul class="sub">
                            <li><a href="form_component.html">Form Components</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-th"></i>
                            <span>Data Tables</span>
                        </a>
                        <ul class="sub">
                            <li><a href="basic_table.html">Basic Table</a></li>
                            <li><a href="responsive_table.html">Responsive Table</a></li>
                        </ul>
                    </li> -->


                </ul>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--sidebar end-->

        <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
        <!--main content start-->
        <section id="main-content">
            <section class="wrapper site-min-height">
                <h3><i class="fa fa-angle-right"></i> 商品新增</h3>
                <div class="row mt">
                    <div class="col-lg-12">
                        <div id="app">
                            <div class="text-end mx-3">
                                <button type="button" class="btn btn-primary" v-on:click="additem">新增</button>
                            </div>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>產品編號</th>
                                        <th>產品名稱</th>
                                        <th>價格</th>
                                        <th>封面</th>
                                        <th>遊戲種類</th>
                                        <th>遊戲平台種類</th>
                                        <th>遊戲公司</th>
                                        <th>銷售狀態</th>
                                        <th>產品描述</th>
                                        <th>UpcNum</th>
                                        <th>產品圖片2</th>
                                        <th>產品圖片3</th>
                                        <th>編輯</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in datastore" :key="item.ProductNo"
                                        v-bind:class="{'table-success':item.onStock}">
                                        <td>{{item.productNo}}</td>
                                        <td>{{item.productName}}</td>
                                        <td>{{item.productPrice}}</td>
                                        <td>
                                            <img :src="item.picList[0].imageUrl" alt="" height="100">
                                        </td>
                                        <td>{{item.gameTypeNo}}</td>
                                        <!-- 要改成中文 -->
                                        <td>{{item.gamePlatformNo}}</td>
                                        <td>{{item.gameCompanyNo}}</td>
                                        <td>{{item.productState}}</td>
                                        <td>{{item.itemProdDescription}}</td>
                                        <td>{{item.upcNum}}</td>
                                        <td>
                                            <img :src="item.picList[1].imageUrl" alt="" height="100">
                                        </td>
                                        <td>
                                            <img :src="item.picList[2].imageUrl" alt="" height="100">
                                        </td>
                                        <td><button type="button" class="btn btn-outline-primary"
                                                v-on:click="edit(item)">編輯</button></td>
                                    </tr>
                                </tbody>
                            </table>
                            <!--   v-if="isNew" 判斷要不要打開編輯的表單  -->
                            <!--   form內容要進去資料庫  -->
                            <div style="width: 30%;">
                                <form v-if="isNew" method="post" action="/CGA101G1/product/GetParameter"
                                    enctype="multipart/form-data" required>
                                    <!-- <form method="post" action="GetParameter" enctype="multipart/form-data"> -->
                                    <div>
                                        <label for="productNo" class="form-label">產品編號</label>
                                        <input type="text" id="productNo" name="ProductNo" class="form-control"
                                            v-model="temp.productNo" readonly>
                                    </div>
                                    <div>
                                        <label for="productName" class="form-label">產品名稱</label>
                                        <input type="text" id="productName" name="ProductName" class="form-control"
                                            v-model="temp.productName" @change="checkdata($event)">
                                    </div>
                                    <div>
                                        <label for="productPrice" class="form-label">價格</label>
                                        <input type="number" id="productPrice" name="ProductPrice" class="form-control"
                                            v-model="temp.productPrice">
                                    </div>
                                    <!-- 要改 -->
                                    <div>
                                        <label for="productState" class="form-label">銷售狀態</label>
                                        <input type="radio" id="productStateUn" name="ProductState"
                                            v-model="temp.productStateUn" value="0" checked>
                                        <label for="productStateUn" class="form-label">未上架</label>
                                        <input type="radio" id="productStateS" name="ProductState"
                                            v-model="temp.productStateS" value="1">
                                        <label for="productStateS" class="form-label">已上架</label>
                                    </div>
                                    <div>
                                        <label for="gameTypeNo" class="form-label">遊戲種類</label>
                                        <input type="number" id="gameTypeNo" name="GameTypeNo" class="form-control"
                                            v-model="temp.gameTypeNo">
                                    </div>
                                    <div>
                                        <label for="gamePlatformNo" class="form-label">遊戲平台種類</label>
                                        <input type="number" id="gamePlatformNo" name="GamePlatformNo"
                                            class="form-control" v-model="temp.gamePlatformNo">
                                    </div>
                                    <div>
                                        <label for="gameCompanyNo" class="form-label">遊戲公司</label>
                                        <input type="number" id="gameCompanyNo" name="GameCompanyNo"
                                            class="form-control" v-model="temp.gameCompanyNo">
                                    </div>
                                    <div>
                                        <label for="upcNum" class="form-label">UpcNum</label>
                                        <input type="number" id="upcNum" name="UpcNum" class="form-control"
                                            v-model="temp.upcNum">
                                    </div>
                                    <div>
                                        <label for="itemProdDescription" class="form-label">遊戲描述</label>
                                        <textarea name="ItemProdDescription" id="itemProdDescription" cols="55"
                                            rows="20">{{temp.itemProdDescription}}</textarea>
                                    </div>


                                    <!-- <div>
                                        <label v-if="url2" class="form-label">封面照片編號</label>
                                        <input v-if="url2" type="text" name="productImage1NO" class="form-control"
                                            v-model="temp.picList[0].productPicNo" readonly>
                                    </div> -->
                                    <div>
                                        <label for="productImage1" class="form-label">封面照片</label>
                                        <img v-if="url" :src="url" class="img-fluid d-block" alt="" width="100">
                                        <input type="file" id="productImage1" name="productImage1" class="form-control"
                                            v-on:change="onFileChange">
                                    </div>

                                    <!-- <div>
                                        <label v-if="url2" class="form-label">產品圖片2編號</label>
                                        <input v-if="url2" type="text" name="productImage2NO" class="form-control"
                                            v-model="temp.picList[1].productPicNo" readonly>
                                    </div> -->
                                    <div>
                                        <label for="productImage2" class="form-label">產品圖片2</label>
                                        <img v-if="url2" :src="url2" class="img-fluid d-block" alt="" width="100">
                                        <input type="file" id="productImage2" name="productImage2" class="form-control"
                                            v-on:change="onFileChange2">
                                    </div>

                                    <!-- <div>
                                        <label v-if="url3" class="form-label">產品圖片3編號</label>
                                        <input v-if="url3" type="text" name="productImage3NO" class="form-control"
                                            v-model="temp.picList[2].productPicNo" readonly>
                                    </div> -->
                                    <div>
                                        <label for="productImage3" class="form-label">產品圖片3</label>
                                        <img v-if="url3" :src="url3" class="img-fluid d-block" alt="" width="100">
                                        <input type="file" id="productImage3" name="productImage3" class="form-control"
                                            v-on:change="onFileChange3">

                                    </div>

                                    <input type="SUBMIT" v-on:click="confirmEdit">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

        </section><!-- /MAIN CONTENT -->

        <!--main content end-->
        <!--footer start-->
        <footer class="site-footer">
            <div class="text-center">
                2014 - Alvarez.is
                <a href="blank.html#" class="go-top">
                    <i class="fa fa-angle-up"></i>
                </a>
            </div>
        </footer>
        <!--footer end-->
    </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->

    <script>
        //custom select box

        // $(function () {
        //     $('select.styled').customSelect();
        // });

        const App = {
            data() {
                return {
                    datastore: [],
                    picData: [],
                    temp: {},
                    isNew: false,
                    url: null,
                    url2: null,
                    url3: null,
                }
            },
            methods: {
                getProductData() {
                    const apiUrl = 'http://localhost:8081/CGA101G1/product/OneProductToJson';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductData的狀態：" + response.status); //狀態碼

                            this.datastore = response.data;//塞進去
                            console.log("datastore讀取資料ok");
                            this.getPicData();
                        });
                },
                getPicData() {
                    const apiUrl = 'http://localhost:8081/CGA101G1/product/ProductPics';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取pic的狀態：" + response.status); //狀態碼

                            this.picData = response.data;//塞進去
                            this.insertPic(this.picData);

                        });
                },
                additem() {
                    this.isNew = true;
                    this.temp = {};
                    this.url = null;
                    this.url2 = null;
                    this.url3 = null;
                },
                edit(para) {
                    this.temp = {};
                    this.temp = { ...para };//淺層拷貝，把點選編輯的物件放進temp
                    this.url = this.temp.picList[0].imageUrl;
                    this.url2 = this.temp.picList[1].imageUrl;
                    this.url3 = this.temp.picList[2].imageUrl;

                },
                onFileChange(e) {
                    this.url = "";
                    const file = e.target.files[0];
                    this.url = URL.createObjectURL(file);
                },
                onFileChange2(e) {
                    this.url2 = "";
                    const file2 = e.target.files[0];
                    this.url2 = URL.createObjectURL(file2);
                },
                onFileChange3(e) {
                    this.url3 = "";
                    const file3 = e.target.files[0];
                    this.url3 = URL.createObjectURL(file3);
                },
                insertPic(picData) {
                    console.log("insertPic開始");
                    for (j = 0; j < this.datastore.length; j++) {
                        this.datastore[j].picList = [];
                        for (i = 0; i < picData.length; i++) {
                            if (picData[i].productNo == this.datastore[j].productNo) {
                                this.datastore[j].picList.push(picData[i]);
                            }
                        }
                    }
                    this.picData = [];
                    console.log("insert完畢");
                },


                confirmEdit() {
                }

            },
            created() {
                this.getProductData();

            }
        };

        Vue.createApp(App).mount('#app');

    </script>

</body>

</html>