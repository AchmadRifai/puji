<%@page contentType="text/html" pageEncoding="UTF-8"%><%
    util.General.ajarAdmin(request, response);
%><!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
   <meta http-equiv="refresh" content="5">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Daftar Bahan Admin | Ayam Bakar Wong Solo</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="dist/css/skins/skin-green.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="dash.php" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>Pu</b>Ji</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Puji</b>Wahyono</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="logout.php"><i class="fa fa-sign-out"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <aside class="main-sidebar">
      <section class="sidebar">
          <div class="user-panel">
              <div class="pull-left info">
                  <p>Admin Area</p>
                  <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
              </div>
          </div>
          <ul class="sidebar-menu" data-widget="tree">
              <li class="header">Tools</li>
              <li><a href="dash-admin.jsp"><i class="fa fa-home"></i> <span>Home</span></a></li>
              <li class="active"><a href="semuaBahan.jsp"><i class="fa fa-bank"></i> <span>Persediaan</span></a></li>
          </ul>
      </section>
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Admin Dashboard</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
        <li>Admin</li>
        <li>Daftar</li>
        <li class="active">Bahan</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Daftar Persediaan</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="table-responsive">
                <table class="table no-margin">
                  <thead>
                  <tr>
                    <th>Nama</th>
                    <th>Jumlah</th>
                    <th>Aksi</th>
                  </tr>
                  </thead>
                  <tbody>
                      <%
try{
    util.Db d=new util.Db();
    java.sql.ResultSet r=d.getResult("select*from persediaan");
    while(r.next()){
        %>
        <tr>
            <td><% out.print(r.getString("nama")); %></td>
            <td><% out.print(""+r.getInt("stok")+" "+r.getString("satuan")); %></td>
            <td>
                <a href="editBahan.exe?kode=<% out.print(r.getString("kode")); %>" class="btn btn-warning"><i class="fa fa-edit"> Edit</i></a> 
                <a href="#" data-toggle="modal" data-target="#delBahan<%out.print(r.getString("kode")); %>" class="btn btn-danger">
                    <i class="fa fa-remove"> Delete</i>
                </a>
                <a href="kulaan.exe?kode=<%out.print(r.getString("kode")); %>" class="btn btn-primary">
                    <i class="fa fa-ship"></i> Kulaan
                </a>
                <div id="delBahan<%out.print(r.getString("kode")); %>" role="modal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Hapus Bahan <%out.print(r.getString("kode")); %></h4>
                            </div>
                            <div class="modal-body">
                                <p>Hapus Bahan ini?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <a href="delBahan.exe?kode=<%out.print(r.getString("kode")); %>" class="btn btn-danger">OK</a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
                      <%
    }r.close();
    d.close();
}catch(Exception ex){util.Db.hindar(ex, request.getRemoteAddr());}
                      %>
                  </tbody>
                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix">
              <a href="tambahBahan.jsp" class="btn btn-sm btn-info btn-flat pull-left">Tambah Bahan</a>
            </div>
            <!-- /.box-footer -->
          </div>
      <!--------------------------
        | Your Page Content Here |
        -------------------------->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      Ayam Bakar Wong Solo
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; <%
        java.util.Date d=new java.util.Date();
        out.print(d.getYear()+1900);
        %> <a href="#">Puji Wahyono</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>