import { useState } from "react";

export function BuyButton({ product }) {
  const [stock, setStock] = useState(product.stock);

  async function handleBuy() {
    setStock(stock - 1);
    await fetch("/api/sales", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ productId: product.id, quantity: 1 }),
    });
  }

  return (
    <div>
      <span>{stock} left</span>
      <button onClick={handleBuy} disabled={stock <= 0}>Buy</button>
    </div>
  );
}
