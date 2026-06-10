// The Play page: real blackjack you actually play (bet, hit/stand/double/split)
// against the dealer, dealt from a server-provided predetermined shoe. The
// counting payoff is sizing your bets by your own running count.
//
// P1 scaffold — the betting/engine/table pieces land in P2–P4.
function Play() {
  return (
    <section className="play">
      <h2>Play</h2>
      <p className="play__placeholder">
        Real blackjack is coming together here — deal, bet, and play out the shoe.
      </p>
    </section>
  );
}

export default Play;
