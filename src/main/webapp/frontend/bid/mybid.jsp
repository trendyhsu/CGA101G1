<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
                    <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">











                            <table class="table table-bordered table-hover mb-0">
                                <thead class="text-700 bg-gray-200">
                                    <tr>
                                        <th class="fw-600">Order #</th>
                                        <th class="fw-600">Date Purchased</th>
                                        <th class="fw-600">Status</th>
                                        <th class="fw-600 text-end">Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-danger m-0">Cancel</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-success m-0">Delivered</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-warning m-0">On Hold</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>

                                </tbody>
                            </table>
                            
                        </div>
					</div>
                    <!-- End Content -->
               	</div>
            </div>
        </div>
        <!--Table -->
    </main>
    <!-- End Main -->
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>