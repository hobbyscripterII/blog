function toggleMenu() {
  let $mainMenu = $('#main-menu');
  
  $mainMenu.toggleClass('active');
}

function toggleSubmenu(event, element) {
  if ($(window).width() <= 768) {
      event.preventDefault();
      
      $(element).parent().toggleClass('active');
  }
}

window.addEventListener('resize', function() {
  let $mainMenu = $('#main-menu');
  let $subMenu = $('nav > ul > li');
  
  if ($(window).width() > 768) {
      $mainMenu.removeClass('active');
      $subMenu.removeClass('active');
  }
});

function alertModalOpen(message) {
    const modal = document.getElementById('alertModal');
    const modalMessage = document.getElementById('modalMessage');
    
    modalMessage.textContent = message;
    
    modal.classList.add('show');
}

function alertModalClose(event) {
    const modal = document.getElementById('alertModal');
    
    modal.classList.remove('show');
}

document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        alertModalClose();
    }
});