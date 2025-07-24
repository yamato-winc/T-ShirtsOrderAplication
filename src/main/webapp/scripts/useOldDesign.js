function useOldDesign(design) {
  // ベースカラー
  const baseColorSelect = document.getElementById('base-color');
  baseColorSelect.value = design.baseColor;
  ChangeBaseColor();

  // 上段文字
  document.getElementById('upper-text-input').value = design.Text1;

  // 上段文字サイズ
  document.querySelectorAll('input[type=number]')[0].value = design.Text1Size;

  // 上段フォントカラー
  document.getElementById('color-box').style.backgroundColor = design.Text1FontColorId;

  // 下段文字
  document.getElementById('lower-text-input').value = design.Text2;

  // 下段文字サイズ
  document.querySelectorAll('input[type=number]')[1].value = design.Text2Size;

  // 文字の縦位置
  document.querySelectorAll('input[type=number]')[2].value = design.verticalPosition;

  // 文字の横位置
  document.querySelectorAll('input[type=number]')[3].value = design.sidePosition;

  // 必要ならここで再描画処理を行う。
  if (typeof renderDesign === 'function') {
    renderDesign(); // デザイン描画関数
  }
}