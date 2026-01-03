function search() {
  let search = $('#search').val().trim();
  
  if(!search) {
    alertModalOpen('검색어를 입력해주세요.');
    search.text('');
  } else {
    location.href = `/book?search=${search}`;
  }
}
  
function bookModalOpen(ihighlight) {
  ihighlight = ihighlight || 0;
  
  $.ajax({
    type : 'GET',
    url : `/book/${ihighlight}`,
    dataType : 'json',
    contentType : 'application/json',
    success : (result) => {
      let status = result.status;
      
      if(status == '200') {
        let data = result.data;
        let $modal = $('#quote-modal');

        if (data) {
          let $modalBookTitle = $('#modal-book-title');
          let $modalBookAuthor = $('#modal-book-author');
          let $modalQuoteText = $('#modal-quote-text');
          let $modalDate = $('#modal-date');
          
          let title = data.title;
          let author = data.author;
          let highlight = data.highlight;
          let createdAt = data.createdAt;
          
          $modalBookTitle.html(title);
          $modalBookAuthor.html(author);
          $modalQuoteText.html(highlight);
          $modalDate.html(createdAt);
          
          $modal.addClass('show');
          document.body.style.overflow = 'hidden';
        }
      } else {
        let errors = result.errors;
        let message = result.message;
        let text = errors || message;
        
        alertModalOpen(text);
      }
    },
    error: (x) => {
      console.log(x);
    }
  });
}

function bookModalClose() {
    let $modal = $('#quote-modal');
    
    $modal.removeClass('show');
    document.body.style.overflow = 'auto';
}

document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        closeModal();
    }
});