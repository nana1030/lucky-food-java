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

  
// 画面が完全に読み込まれたらスタート！
document.addEventListener("DOMContentLoaded", () => {
  // 1. HTMLの部品（セレクトボックスやボタンなど）を取得して変数に入れる
  const yearSelect = document.getElementById("year");
  const monthSelect = document.getElementById("month");
  const daySelect = document.getElementById("day");
  const sendBtn = document.getElementById("sendBtn");
  const loadingDiv = document.getElementById("loading");

  // 2. 「年」の選択肢を自動生成（1950年〜2026年まで）
  for (let i = 2026; i >= 1950; i--) {
    let option = document.createElement("option");
    option.value = i;
    option.textContent = i + "年";
    yearSelect.appendChild(option);
  }

  // 3. 「月」の選択肢を自動生成（1月〜12月まで）
  for (let i = 1; i <= 12; i++) {
    let option = document.createElement("option");
    option.value = i;
    option.textContent = i + "月";
    monthSelect.appendChild(option);
  }

  // 4. 「日」の選択肢を自動生成（1日〜31日まで）
  for (let i = 1; i <= 31; i++) {
    let option = document.createElement("option");
    option.value = i;
    option.textContent = i + "日";
    daySelect.appendChild(option);
  }

  // 5. 送信ボタンがクリックされた時の処理
  sendBtn.addEventListener("click", () => {
    const year = yearSelect.value;
    // 月と日を「2桁の文字列（例: 5月→05、9日→09）」に変換する
    const month = monthSelect.value.padStart(2, '0');
    const day = daySelect.value.padStart(2, '0');

    // バックエンドが受け取れる「YYYY-MM-DD」の形に合体
    const birthdayStr = `${year}-${month}-${day}`;

    // 🌀 CSSで定義されているローディング画面を表示する（hiddenクラスを取り除く）
    if (loadingDiv) {
      loadingDiv.classList.remove("hidden");
    }
    // 連打できないようにボタンを無効化（CSSの button:disabled が発動します）
    sendBtn.disabled = true;

    // 6. メンバーAさんのAPI（Spring Boot）へデータを送信（fetch）
    fetch("/api/birthday", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      // サーバーに「birthday=1981-05-09」の形で送る
      body: JSON.stringify({ birthdate: birthdayStr })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error("サーバーとの通信に失敗しました。");
      }
      return response.json(); // メンバーAさんから届いたデータを解釈
    })
    .then(data => {
      // 7. 届いた診断結果データを、ブラウザの一時保存エリア（セッションストレージ）に保存
      sessionStorage.setItem("diagnoseResult", JSON.stringify(data));

      // 8. メンバーDさんの描画開始の引き金（トリガー）として、結果画面へジャンプ！
      window.location.href = "result.html";
    })
    .catch(error => {
      // 万が一通信エラーが起きたら、ローディングを消してボタンを元に戻す
      alert("エラー: " + error.message);
      if (loadingDiv) {
        loadingDiv.classList.add("hidden");
      }
      sendBtn.disabled = false;
    });
  });
});