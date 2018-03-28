package camo.mailru.api.json;

/**
 * Created by AlekseevGA on 16.03.2018.
 */

/// <summary>
/// Object to response parsing.
/// </summary>
public enum JsonObjectType
{
    /// <summary>
    /// Authorization token.
    /// </summary>
    Token,

    /// <summary>
    /// List of items.
    /// </summary>
    Entry,

    /// <summary>
    /// Servers info.
    /// </summary>
    Shard,

    /// <summary>
    /// Full body string.
    /// </summary>
    BodyAsString,

    /// <summary>
    /// Disk space usage.
    /// </summary>
    DiskUsage
}
