package InventoryManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseOrder implements Serializable {

    private int purchaseOrderId;
    private int supplierId;
    private Date date;
    private List<PurchaseLine> lines;
    private boolean received;

    public PurchaseOrder(int purchaseOrderId, int supplierId) {
        this.purchaseOrderId = purchaseOrderId;
        this.supplierId = supplierId;
        this.date = new Date();
        this.lines = new ArrayList<>();
        this.received = false;
    }

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public Date getDate() {
        return date;
    }

    public List<PurchaseLine> getLines() {
        return lines;
    }

    public boolean isReceived() {
        return received;
    }

    public void addLine(PurchaseLine line) {
        lines.add(line);
    }

    public double totalCost() {
        return lines.stream().mapToDouble(PurchaseLine::lineTotal).sum();
    }

    public void markReceived() {
        received = true;
    }

    @Override
    public String toString() {
        return String.format("PurchaseOrder %d | Supplier %d | Date: %s | Lines: %d | Total: %.2f | Received: %s",
                purchaseOrderId, supplierId, date.toString(), lines.size(), totalCost(), received ? "Yes" : "No");
    }
}
