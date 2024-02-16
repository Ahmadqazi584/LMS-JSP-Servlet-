<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Model.Category" %>
<%@ page import="java.io.*" %>
<%@ page import="Dao.*" %>
<%@ page import="DaoImpl.*" %>

 
        <%
            if(session.getAttribute("username") == null){
    			response.sendRedirect("adminlogin.jsp");
            }
        %>
<%@ include file="includes/header.jsp" %>

<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">

<div class="wrapper">

  <!-- Navbar -->
  <%@ include file="includes/navigation.jsp" %>

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <span class="brand-text font-weight-light">Online Library System</span>
    </a>

    <!-- Sidebar -->
    <%@ include file="includes/sidebar-mainmenu.jsp" %>


  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">welcome, ${username}</h1>
            
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Category</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      
      
      
       <div class="row">
          <!-- left column -->
          <div class="col-md-4">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Add Book Category</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form action="AddCategoryyServlet" method="post" >
                <div class="card-body">
                  <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputEmail1" name="addcategory" placeholder="your category">
                  </div>
                  <select class="custom-select rounded-0" id="exampleSelectRounded0" name="addstatus">
                    <option>Select Status</option>
                    <option>Active</option>
                    <option>In Active</option>
                  </select>
                </div>
                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Insert</button>
                </div>
              </form>
            </div>
            <!-- /.card -->
            
            <% 
            CategoryDao categorydao = new CategoryDaoImpl();
            String action = request.getParameter("action");
    //		int categoryid = Integer.parseInt(request.getParameter("category_id").toString());
            if("UPDATE".equals(action)) {
                int categoryid = Integer.parseInt(request.getParameter("category_id").toString());                
    	        Category category = categorydao.getCategoryById(categoryid);
            %>
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Update Book Categories</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form action="UpdateCategorysServlet" method="post" >
                <div class="card-body">
                  <div class="form-group">
                    <input type="number" readonly class="form-control" id="exampleInputEmail1"  name="categoryid" value="<%= category.getId() %>" placeholder="your category">
                  </div>
                
                  <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputEmail1" name="updatecategory" value="<%= category.getCategoryname() %>" placeholder="your category">
                  </div>
                  <select class="custom-select rounded-0" id="exampleSelectRounded0" name="updatestatus">
                    
                    <option>
                    <% if(category.getStatus() == true){
                  	  out.print("Active");
                    }else{
                  	  out.print("In Active");
                    }
                    %></option>
                    
                    <option>
                    <% if(category.getStatus() == false){
                  	  out.print("Active");
                    }else{
                  	  out.print("In Active");
                    }
                    %></option>
                   
                  </select>
                </div>
                <div class="card-footer">
                  <button type="submit" class="btn btn-primary" name="updatebtn">Update</button>
                </div>
              </form>
            </div>
                
          <% }             
          %>
          </div>
      
          
          <!--/.col (left) -->
          <!-- right column -->
          <div class="col-md-8">
            <!-- Form Element sizes -->
            <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Category List</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                      <button type="submit" class="btn btn-default">
                        <i class="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
             
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Category</th>
                      <th>Status</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    
                      <%  
                      CategoryDao cd = new CategoryDaoImpl();
                      List<Category> datalist = new ArrayList<>();
                      datalist = cd.getAllCategory();
                      for(Category c : datalist){
                    	  
                      %>
                      <tr>
                      <td><%= c.getId() %></td>
                      <td><%= c.getCategoryname() %></td>
                      <td><% if(c.getStatus() == true){
                    	  out.print("Active");
                      }else{
                    	  out.print("In Active");
                      }
                    	  
                    	  %></td>
                      <td>
                      <a href='category.jsp?action=UPDATE&category_id=<%= c.getId() %>' class="btn btn-success">Edit</a>
                      <a href='UpdateCategorysServlet?action=DELETE&category_id=<%= c.getId() %>' class="btn btn-danger">Delete</a>
                      </td>
                      </tr>
                      <% } %>
                    
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
            
            
          </div>
<%


%>

          
          <!--/.col (right) -->
        </div>
      </div>
    </section>
  </div>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <%@ include file="includes/footer.jsp" %>
  