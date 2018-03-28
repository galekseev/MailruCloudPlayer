package camo.mailru.api.json;


/**
 * File size definition.
 */
public class FileSize {

    public FileSize(long defaultValue){
        setValue(defaultValue);
    }

    private long defValue = 0;
    /**
     * Gets default size in bytes.
     *
     * @return File size.
     */
    public long getValue() {
        return this.defValue;
    }

    public void setValue(long value) {
        this.defValue = value;
        this.SetNormalizedValue();
    }

    private float NormalizedValue;
    /**
     * Gets normalized  file size, auto detect storage unit.
     *
     * @return File size.
     */
    public float getNormalizedValue() {
        return NormalizedValue;
    }

    private StorageUnit NormalizedType;
    /**
     * Gets auto detected storage unit by normalized value.
     */
    public StorageUnit getNormalizedType() {
        return NormalizedType;
    }

    /**
     * Normalized value detection and auto detection storage unit.
     */
    private void SetNormalizedValue() {
        if (this.defValue < 1024L) {
            this.NormalizedType = StorageUnit.Byte;
            this.NormalizedValue = (float) this.defValue;
        } else if (this.defValue >= 1024L && this.defValue < 1024L * 1024L) {
            this.NormalizedType = StorageUnit.Kb;
            this.NormalizedValue = (float) this.defValue / 1024f;
        } else if (this.defValue >= 1024L * 1024L && this.defValue < 1024L * 1024L * 1024L) {
            this.NormalizedType = StorageUnit.Mb;
            this.NormalizedValue = (float) this.defValue / 1024f / 1024f;
        } else if (this.defValue >= 1024L * 1024L * 1024L && this.defValue < 1024L * 1024L * 1024L * 1024L) {
            this.NormalizedType = StorageUnit.Gb;
            this.NormalizedValue = (float) this.defValue / 1024f / 1024f / 1024f;
        } else {
            this.NormalizedType = StorageUnit.Tb;
            this.NormalizedValue = (float) this.defValue / 1024f / 1024f / 1024f / 1024f;
        }
    }
}