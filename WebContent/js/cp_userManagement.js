$('.choose-role > li').click(function () {
	if($(this).hasClass('selected')) {
		return;
	} else {
		$(this).addClass('selected').siblings().removeClass('selected');
		
	}
});

$('.editRole .choose-role > li').click(function () {
	var role = $(this).attr('title');
	sessionStorage.setItem('role_1',role);
});

function getRole(role,account) {
	sessionStorage.setItem('role_',role);
	sessionStorage.setItem('role_1',role);
	sessionStorage.setItem('account_',account);
    $('.editRole .choose-role > li[title=' + role + ']').addClass('selected').siblings().removeClass('selected');
}
//getRole('0');