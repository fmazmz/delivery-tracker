import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { ParcelClient } from "../parcel/ParcelClient";

const parcelClient = new ParcelClient(import.meta.env.VITE_API_BASE_URL);

export default function TrackPage() {
    const { trackingNumber } = useParams();
    const [parcel, setParcel] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!trackingNumber) return;

        async function load() {
            try {
                const result = await parcelClient.getParcel(trackingNumber);
                setParcel(result);
            } catch (e) {
                setError("Failed to load parcel");
            }
        }

        load();
    }, [trackingNumber]);

    return (
        <div>
            <h1>Track Parcel</h1>

            {error && <p>{error}</p>}

            {parcel && (
                <div>
                    <p>Number: {parcel.trackingNumber}</p>
                    <p>Status: {parcel.status}</p>
                    <p>Location: {parcel.location}</p>
                </div>
            )}
        </div>
    );
}