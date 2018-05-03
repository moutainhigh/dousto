<script type="text/javascript">
    $().ready(function(){
       /* var $menuItem = $("#menu .menuItem");
        var $previousMenuItem;
        $menuItem.click(function(){
            var $this = $(this);
            if ($previousMenuItem != null) {
                $previousMenuItem.removeClass("current");
            }
            $previousMenuItem = $this;
            $this.addClass("current");
        })*/
		jQuery('ul.sf-menu').superfish();
    })
</script>
<div class="header">
    <div class="bodyLeft">
        <div class="logo">
        </div>
    </div>
    <div class="bodyRight">
        <div class="link">
            <span class="welcome"><strong><@sec.authentication property="name" /></strong>&nbsp;您好!&nbsp;</span>
        </div>
        <div id="menu" class="menu">
            <ul class="sf-menu">
                <li class="menuItem">
                    <a href="#"  hidefocus="true">商品管理</a>
					<ul>
					<li><a href="product!list.action">商品列表</a></li>
					<li><a href="product!add.action">添加商品</a></li>
					<li>
						<a href="#">商品分类管理</a>
						<ul>
							<li><a href="product_category!list.action">分类列表</a></li>
							<li><a href="product_category!add.action">添加分类</a></li>
						</ul>
					</li>
					<li>
						<a href="#">商品类型管理</a>
						<ul>
							<li><a href="product_type!list.action">类型列表</a></li>
							<li><a href="product_attribute!list.action">属性列表</a></li>
						</ul>
					</li>
					<li>
						<a href="#">品牌管理</a>
						<ul>
							<li><a href="brand!list.action">品牌列表</a></li>
							<li><a href="brand!add.action">添加品牌</a></li>
						</ul>
					</li>
				</ul>
                </li>
                <li class="menuItem">
                    <a href="menu!order.action"  hidefocus="true">订单处理</a>
                </li>
                <li class="menuItem">
                    <a href="menu!member.action"  hidefocus="true">会员管理</a>
                </li>
                <li class="menuItem">
                    <a href="menu!content.action"  hidefocus="true">页面内容</a>
                </li>
                <li class="menuItem">
                    <a href="#"  hidefocus="true">管理员</a>
					<ul>
						<li><a href="product!list.action">商品列表</a></li>
						<li><a href="product!add.action">添加商品</a></li>
						<li>
							<a href="#">商品分类管理</a>
							<ul>
								<li><a href="product_category!list.action">分类列表</a></li>
								<li><a href="product_category!add.action">添加分类</a></li>
							</ul>
						</li>
						<li>
							<a href="#">商品类型管理</a>
							<ul>
								<li><a href="product_type!list.action">类型列表</a></li>
								<li><a href="product_attribute!list.action">属性列表</a></li>
							</ul>
						</li>
						<li>
							<a href="#">品牌管理</a>
							<ul>
								<li><a href="brand!list.action">品牌列表</a></li>
								<li><a href="brand!add.action">添加品牌</a></li>
							</ul>
						</li>
					</ul>
                </li>
                <li class="menuItem">
                    <a href="menu!setting.action"  hidefocus="true">网站设置</a>
                </li>
                <li class="home">
                    <a href="${base}/" target="_blank" hidefocus="true">网站首页</a>
                </li>
            </ul>
            <div class="info">
                <a class="profile" href="admin_profile!edit.action" target="mainFrame">个人资料</a>
                <a class="logout" href="${base}/admin/logout" target="_top">退出</a>
            </div>
        </div>
    </div>
</div>
