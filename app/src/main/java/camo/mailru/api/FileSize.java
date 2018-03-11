package camo.mailru.api;


/// <summary>
/// File size definition.
/// </summary>
public class FileSize {

    public FileSize(long defaultValue){
        setDefaultValue(defaultValue);
    }

    private long defValue = 0;
    /// <summary>
    /// Gets default size in bytes.
    /// </summary>
    /// <value>File size.</value>
    public long getDefaultValue() {
        return this.defValue;
    }

    public void setDefaultValue(long value) {
        this.defValue = value;
        this.SetNormalizedValue();
    }

    private float NormalizedValue;
    /// <summary>
    /// Gets normalized  file size, auto detect storage unit.
    /// </summary>
    /// <value>File size.</value>
    public float getNormalizedValue() {
        return NormalizedValue;
    }

    private StorageUnit NormalizedType;
    /// <summary>
    /// Gets auto detected storage unit by normalized value.
    /// </summary>
    public StorageUnit getNormalizedType() {
        return NormalizedType;
    }

    /// <summary>
    /// Normalized value detection and auto detection storage unit.
    /// </summary>
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