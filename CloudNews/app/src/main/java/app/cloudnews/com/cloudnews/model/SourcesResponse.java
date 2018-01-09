package app.cloudnews.com.cloudnews.model;

public class SourcesResponse
{
    private String status;

    private Sources[] sources;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Sources[] getSources ()
    {
        return sources;
    }

    public void setSources (Sources[] sources)
    {
        this.sources = sources;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", sources = "+sources+"]";
    }
}

			