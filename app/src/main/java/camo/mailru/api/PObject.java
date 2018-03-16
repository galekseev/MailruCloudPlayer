package camo.mailru.api;

/**
 * Created by AlekseevGA on 16.03.2018.
 */

/// <summary>
/// Object to response parsing.
/// </summary>
public enum PObject
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
