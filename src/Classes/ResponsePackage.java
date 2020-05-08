package Classes;

public class ResponsePackage {

    private String msg;
    private int response;
    private String data;

    public ResponsePackage(){
        this.msg = Status.INTERNAL_SERVER_ERROR.toString();
        this.response = 500;
        this.data = "{}";
    }

    public enum Status {
        OK(200),
        CREATED(201),
        ACCEPTED(202),
        NO_CONTENT(204),
        FOUND(302),
        NOT_MODIFIED(304),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        NOT_ACCEPTABLE(406),
        CONFLICT(409),
        PRECONDITION_FAILED(412),
        INTERNAL_SERVER_ERROR(500),
        NOT_IMPLEMENTED(501);

        private int code;

        Status(int code){
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }

    public int getResponse() {
        return response;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMsgResponse(Status status){
        this.msg = status.toString();
        this.response = status.getCode();
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String formatData(){
        return "{" + "\"msg\":\"" + this.msg + "\",\"data\":" + this.data + "}";
    }
}
