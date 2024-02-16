<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Model.*" %>
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
              <li class="breadcrumb-item active">Author</li>
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
                    <!-- right column -->
          <div class="col-md-12">
            <!-- Form Element sizes -->
            <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Authors List</h3>

                <div class="card-tools">
                  <div class="input-group" >
                  
                    <a href="" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#AddAuthorModel"><i class="fa fa-plus" aria-hidden="true"></i> Add Author</a>
                    
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
             
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Author</th>
                      <th>Added</th>
                      <th>Updated</th>
                      <th>Status</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    
                      <%  
                      AuthorDao ad = new AuthorDaoImpl();
                      List<Author> datalist = new ArrayList<>();
                      datalist = ad.getAllAuthor();
                      for(Author a : datalist){
                    	  
                      %>
                      <tr>
                      <td><%= a.getId() %></td>
                      <td><%= a.getAuthorname() %></td>
                      <td><%= a.getAdded() %></td>
                      <td><%= a.getUpdated() %></td>
                      <td><% if(a.getStatus() == true){
                    	  out.print("Active");
                      }else{
                    	  out.print("In Active");
                      }
                    	  
                    	  %></td>
                      <td>
                      <a href='author.jsp?action=UPDATE&author_id=<%= a.getId() %>' class="btn btn-success" >Edit</a>
                      <a href='UpdateAuthor?action=DELETE&category_id=<%= a.getId() %>' class="btn btn-danger">Delete</a>
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

<!-- Insert Author Modal Popup -->

<div class="modal fade" id="AddAuthorModel" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Insert Author</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="AddAuthor" method="post" >
                <div class="card-body">
                  <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputEmail1" name="addauthorname" placeholder="author name">
                  </div>
                  <select class="custom-select rounded-0" id="exampleSelectRounded0" name="addauthorstatus">
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
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Edit Author Modal Popup -->
<%
AuthorDao authordao = new AuthorDaoImpl();
String action = request.getParameter("action");
if("UPDATE".equals(action)){
    int authorid = Integer.parseInt(request.getParameter("author_id").toString());                
    Author author = authordao.getAuthorById(authorid);
    
%>
<div class="" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Update Author</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="UpdateAuthor" method="post" >
                <div class="card-body">
                <div class="form-group">
                    <input type="number" readonly class="form-control" id="exampleInputEmail1"  name="authorid" value="<%= author.getId() %>">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputEmail1" name="updateauthorname" value="<%= author.getAuthorname() %>" placeholder="author name">
                  </div>
                  <select class="custom-select rounded-0" id="exampleSelectRounded0" name="updateauthorstatus">
                    <option>
                    <% if(author.getStatus() == true){
                  	  out.print("Active");
                    }else{
                  	  out.print("In Active");
                    }
                    %></option>
                    
                    <option>
                    <% if(author.getStatus() == false){
                  	  out.print("Active");
                    }else{
                  	  out.print("In Active");
                    }
                    %></option>
                  </select>
                </div>
                <div class="card-footer">
                  <button type="submit" class="btn btn-success">Update</button>
                </div>
              </form>
      </div>
      
    </div>
  </div>
</div>
<% } %>

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
  