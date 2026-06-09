// 光の粒子アニメーション
function createLight() {
  const light = document.createElement('div');
  light.classList.add('light-particle');
  light.style.left = Math.random() * 100 + 'vw';
  light.style.top = Math.random() * 100 + 'vh';
  light.style.setProperty('--tx', (Math.random() * 100 - 50) + 'px');
  light.style.setProperty('--ty', (Math.random() * -150 - 50) + 'px');
  document.getElementById('leaves-container').appendChild(light);
  setTimeout(() => light.remove(), 4000);
}
setInterval(createLight, 400);

// セッションストレージから診断結果を取得して表示
document.addEventListener("DOMContentLoaded", () => {
  const raw = sessionStorage.getItem("diagnoseResult");

  if (!raw) {
    window.location.href = "/index.html";
    return;
  }

  const data = JSON.parse(raw);
  const area = document.getElementById("resultArea");
  const k = data.kanshi;

  area.innerHTML = `
     <div class="result-item">
       <div class="result-label">年齢</div>
       <div class="result-value accent">${data.age}歳</div>
     </div>
     <hr class="divider">
     <div class="result-item">
       <div class="result-label">干支</div>
       <div class="result-value accent">${k[0]}（${k[1]}）</div>
     </div>
     <hr class="divider">
     <div class="result-item">
       <div class="result-label">五行</div>
       <div class="result-element">${k[2]}</div>
     </div>
     <hr class="divider">
     <div class="result-item">
       <div class="result-label">性格</div>
       <div class="result-value" style="font-size:15px;">${k[3]}</div>
     </div>
     <hr class="divider">
     <div class="result-item">
       <div class="result-label">強み</div>
       <div class="result-value" style="font-size:15px;">${k[4]}</div>
     </div>
     <hr class="divider">
     <div class="result-item">
       <div class="result-label">弱み</div>
       <div class="result-value" style="font-size:15px;">${k[5]}</div>
     </div>
   `;
});