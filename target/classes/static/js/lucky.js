const birth = localStorage.getItem("birth");

// デフォルト画像（保険）
let foodImg = "/images/sushi.png";

if (birth === "1990-01-01") {
  foodImg = "/images/onigiri.png";
} 
else if (birth === "2000-05-10") {
  foodImg = "/images/ramen.png";
} 
else if (birth) {
  // birthがある場合だけハッシュ版にする
  foodImg = getFoodByBirth(birth);
}

// 画像表示
document.getElementById("foodImage").src = foodImg;


// ===== 占いっぽいランダム固定ロジック =====
function getFoodByBirth(str) {
  const foods = [
    "/images/onigiri.png",
    "/images/ramen.png",
    "/images/sushi.png",
    "/images/pasta.png"
  ];

  const index = hashCode(str) % foods.length;
  return foods[index];
}

// ハッシュ関数
function hashCode(str) {
  let hash = 0;
  for (let i = 0; i < str.length; i++) {
    hash = (hash * 31 + str.charCodeAt(i)) >>> 0;
  }
  return hash;
}