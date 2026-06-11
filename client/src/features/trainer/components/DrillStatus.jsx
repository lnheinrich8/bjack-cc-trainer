import { useState } from "react";

// Top-right difficulty label. Hover over it to reveal the current settings
// (decks, players, speed, streak) in a vertical column underneath. `details` is
// a ready-made list of strings built by Trainer.
function DrillStatus({ label, details }) {
    const [hovered, setHovered] = useState(false);

    return (
        <div
            className="drillstatus"
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
        >
            <span className="drillstatus__toggle">{label}</span>

            {hovered && (
                <ul className="drillstatus__details">
                    {details.map((d, i) => (
                        <li key={i}>{d}</li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default DrillStatus;
