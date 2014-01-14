package org.robotninjas.barge.state;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;
import org.robotninjas.barge.RaftException;
import org.robotninjas.barge.log.RaftLog;
import org.robotninjas.barge.proto.RaftEntry.Membership;
import javax.annotation.Nonnull;
import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static org.robotninjas.barge.proto.RaftProto.*;
import static org.robotninjas.barge.state.RaftStateContext.StateType.FOLLOWER;

class Start implements State {

  private final RaftLog log;

  @Inject
  public Start(RaftLog log) {
    this.log = checkNotNull(log);
  }

  @Override
  public void init(@Nonnull RaftStateContext ctx) {
    log.load();
    ctx.setState(this, FOLLOWER);
  }

  @Nonnull
  @Override
  public RequestVoteResponse requestVote(@Nonnull RaftStateContext ctx, @Nonnull RequestVote request) {
    throw new RuntimeException("Service unavailable");
  }

  @Nonnull
  @Override
  public AppendEntriesResponse appendEntries(@Nonnull RaftStateContext ctx, @Nonnull AppendEntries request) {
    throw new RuntimeException("Service unavailable");
  }

  @Nonnull
  @Override
  public ListenableFuture<Object> commitOperation(@Nonnull RaftStateContext ctx, @Nonnull byte[] operation) throws RaftException {
    throw new RaftException("Service has not started yet");
  }

  @Override
  public ListenableFuture<Boolean> setConfiguration(RaftStateContext ctx, long oldId, Membership nextConfiguration)
      throws RaftException {
    throw new RaftException("Service has not started yet");
  }

}